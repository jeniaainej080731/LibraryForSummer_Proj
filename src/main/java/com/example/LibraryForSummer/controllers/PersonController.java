package com.example.LibraryForSummer.controllers;

import com.example.LibraryForSummer.models.Childs.History;
import com.example.LibraryForSummer.models.Childs.Linkage;
import com.example.LibraryForSummer.models.Parent_Child.Book;
import com.example.LibraryForSummer.models.Person;
import com.example.LibraryForSummer.services.BooksService;
import com.example.LibraryForSummer.services.PeopleService;
import com.example.LibraryForSummer.services.forChilds.HistoryService;
import com.example.LibraryForSummer.services.forChilds.LinkageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PeopleService peopleService;
    private final BooksService booksService;
    private final HistoryService historyService;
    private final LinkageService linkageService;

    @Autowired
    public PersonController(PeopleService peopleService, BooksService booksService, HistoryService historyService, LinkageService linkageService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
        this.historyService = historyService;
        this.linkageService = linkageService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        Person person = peopleService.findById(id);

        List<Linkage> linkages = linkageService.findAll();

        List<Book> assignedBooks = linkages.stream()
                .filter(linkage -> linkage.getPerson().getId() == id)
                .map(Linkage::getBook)
                .collect(Collectors.toList());

        List<Book> allBooks = booksService.findAll();

        List<Book> availableBooks = allBooks.stream()
                .filter(book -> assignedBooks.stream().noneMatch(b -> b.getId() == book.getId()) && !book.isExpired())
                .collect(Collectors.toList());

        model.addAttribute("person", person);
        model.addAttribute("assignedBooks", assignedBooks);
        model.addAttribute("availableBooks", availableBooks);

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }

    @PostMapping("/{id}/assignBook")
    public String assignBookToPerson(@PathVariable("id") int personId,
                                     @RequestParam("bookId") int bookId) {
        Person person = peopleService.findById(personId);
        Book book = booksService.findById(bookId);
        if (person != null && book != null) {
            History history = new History(person, book, new Date());
            historyService.save(history);

            booksService.save(book);

            linkageService.assignBook(personId, bookId);
        }
        return "redirect:/people/" + personId;
    }

    @PostMapping("/{personId}/return")
    public String returnBook(@PathVariable("personId") int personId,
                             @RequestParam("bookId") int bookId) {
        Person person = peopleService.findById(personId);
        Book book = booksService.findById(bookId);

        if (person != null && book != null) {
            List<History> histories = historyService.findByPersonIdAndBookId(personId, bookId);
            if (histories != null && !histories.isEmpty()) {
                for (History history : histories) {
                    // Check if this record matches and has a null releaseDate
                    if (history.getBook().getId() == bookId &&
                            history.getPerson().getId() == personId &&
                            history.getReleaseDate() == null) {

                        history.setReleaseDate(new Date());
                        historyService.save(history);
                        break; // Exit loop after updating the specific record
                    }
                }
                linkageService.removeBook(personId, bookId);

                booksService.save(book);

                return "redirect:/people/" + personId;
            }
        }
        // Return to the people list in case of error
        return "redirect:/people";
    }

}
