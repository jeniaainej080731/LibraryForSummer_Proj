package com.example.LibraryForSummer.controllers;

import com.example.LibraryForSummer.models.Childs.History;
import com.example.LibraryForSummer.models.Parent_Child.Book;
import com.example.LibraryForSummer.models.Parents.*;
import com.example.LibraryForSummer.models.Person;
import com.example.LibraryForSummer.repositories.forParents.GenresRepository;
import com.example.LibraryForSummer.repositories.forParents.TypesRepository;
import com.example.LibraryForSummer.services.BooksService;
import com.example.LibraryForSummer.services.PeopleService;
import com.example.LibraryForSummer.services.forChilds.HistoryService;
import com.example.LibraryForSummer.services.forChilds.LinkageService;
import com.example.LibraryForSummer.services.forParents.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BooksService booksService;
    private final PeopleService peopleService;
    private final HistoryService historyService;
    private final GenresRepository genresRepository;
    private final TypesRepository typesRepository;
    private final GenresService genresService;
    private final TypesService typesService;
    private final LinkageService linkageService;
    private final Photos_url_Service photos_url_Service;
    private final DescriptionsService descriptionsService;

    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService, HistoryService historyService, GenresRepository genresRepository, TypesRepository typesRepository, GenresService genresService, TypesService typesService, LinkageService linkageService, Photos_url_Service photos_url_Service, DescriptionsService descriptionsService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.historyService = historyService;
        this.genresRepository = genresRepository;
        this.typesRepository = typesRepository;
        this.genresService = genresService;
        this.typesService = typesService;
        this.descriptionsService = descriptionsService;
        this.linkageService = linkageService;
        this.photos_url_Service = photos_url_Service;
    }

    @GetMapping()
    public String index(@RequestParam(value = "genre", required = false) Integer genreId,
                        @RequestParam(value = "type", required = false) Integer typeId,
                        Model model) {
        List<Book> books;

        if (genreId != null) {
            Genre genre = genresRepository.findById(genreId).orElse(null);
            books = genre != null ? booksService.findByGenre(genre) : booksService.findAll();
        } else if (typeId != null) {
            Type type = typesRepository.findById(typeId).orElse(null);
            books = type != null ? booksService.findByType(type) : booksService.findAll();
        } else {
            books = booksService.findAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("genres", genresRepository.findAll());
        model.addAttribute("types", typesRepository.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = booksService.findById(id);

        // Фильтрация доступных жанров
        List<Genre> availableGenres = genresService.findAll().stream()
                .filter(genre -> !book.getGenres().contains(genre))
                .toList();

        List<Person> allPeople = peopleService.findAll();
        List<Person> assignedPeople = historyService.findPeopleByBookId(id);

        // Получение списка людей, связанных с книгой через сущность Linkage
        List<Person> linkedPeople = linkageService.findPeopleByBookId(id);

        // Фильтрация людей, которые связаны с книгой через Linkage (для секции People with this Book)
        List<Person> filteredAssignedPeople = assignedPeople.stream()
                .filter(person -> linkedPeople.contains(person))
                .toList();

        // Фильтрация людей, которые не связаны с книгой через Linkage (для комбобокса)
        List<Person> availablePeople = allPeople.stream()
                .filter(person -> !linkedPeople.contains(person))
                .toList();

        // Получение фото и описания книги
        Photo_url photo_url = photos_url_Service.findPhotoUrlByBookId(id);
        Description description = descriptionsService.findDescriptionByBookId(id);

        // Добавление атрибутов модели
        model.addAttribute("book", book);
        model.addAttribute("assignedPeople", filteredAssignedPeople);
        model.addAttribute("availablePeople", availablePeople);
        model.addAttribute("genres", availableGenres);
        model.addAttribute("types", typesService.findAll());
        model.addAttribute("photo_url", photo_url);
        model.addAttribute("description", description);

        return "books/show";
    }


    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/assign")
    public String assignBookToPerson(@PathVariable("id") int bookId,
                                     @RequestParam("personId") int personId) {
        Book book = booksService.findById(bookId);
        Person person = peopleService.findById(personId);
        if (book != null && person != null) {
            History history = new History(person, book, new Date());
            historyService.save(history);

            linkageService.assignBook(personId, bookId);
        }
        return "redirect:/books/" + bookId;
    }

    @PostMapping("/{id}/return")
    public String returnBook(@PathVariable("id") int bookId, @RequestParam("personId") int personId) {
        Book book = booksService.findById(bookId);
        Person person = peopleService.findById(personId);

        if (book != null && person != null) {
            // Найти историю, связанную с книгой и человеком
            List<History> histories = historyService.findByPersonIdAndBookId(personId, bookId);

            for (History history : histories) {
                // Найти конкретный запись с null для releaseDate
                if (history.getBook().getId() == bookId &&
                        history.getPerson().getId() == personId &&
                        history.getReleaseDate() == null) {

                    history.setReleaseDate(new Date());
                    historyService.save(history);
                    break;
                }
            }
            linkageService.removeBook(personId, bookId);
            booksService.save(book);

            return "redirect:/books/" + bookId;
        }

        return "redirect:/books/" + bookId;
    }


    @PostMapping("/{id}/assign-genre")
    public String assignGenre(@PathVariable("id") int id, @RequestParam("genreId") int genreId) {
        Book book = booksService.findById(id);
        Genre genre = genresService.findById(genreId);
        if (book != null && genre != null) {
            booksService.assignGenre(id, genre);
        }
        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/assign-type")
    public String assignType(@PathVariable("id") int id, @RequestParam("typeId") int typeId) {
        Book book = booksService.findById(id);
        Type type = typesService.findById(typeId);
        if (book != null && type != null) {
            // Удаляем старый тип, если он есть
            if (book.getType() != null) {
                book.getType().setBook(null);
            }
            // Назначаем новый тип
            book.setType(type);
            booksService.save(book);
        }
        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/remove-genre")
    public String removeGenre(@PathVariable("id") int id, @RequestParam("genreId") int genreId) {
        booksService.removeGenre(id, genreId);
        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/clear-type")
    public String clearType(@PathVariable("id") int id) {
        booksService.clearType(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/edit-description")
    public String editDescription(@PathVariable("id") int id, Model model) {
        Book book = booksService.findById(id);
        Description description = descriptionsService.findDescriptionByBookId(id);

        model.addAttribute("book", book);
        model.addAttribute("description", description);
        return "books/edit-description";
    }

    @PostMapping("/{id}/update-description")
    public String updateDescription(@PathVariable("id") int id,
                                    @RequestParam("descriptionText") String descriptionText) {
        Description description = descriptionsService.findDescriptionByBookId(id);
        if (description != null) {
            description.setDescription(descriptionText);
            descriptionsService.update(description);
        }
        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/remove-description")
    public String removeDescription(@PathVariable("id") int id) {
        Description description = descriptionsService.findDescriptionByBookId(id);
        descriptionsService.deleteByBookId(id);
        descriptionsService.delete(description.getDescriptionId());
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/add-description")
    public String addDescriptionForm(@PathVariable("id") int id, Model model) {
        Book book = booksService.findById(id);

        if (book != null) {
            model.addAttribute("book", book);
        }

        return "books/add-description";
    }

    @PostMapping("/{id}/add-description")
    public String addDescription(@PathVariable("id") int id, @RequestParam("descriptionText") String descriptionText) {
        Book book = booksService.findById(id);

        if (book != null) {
            Description description = new Description();
            description.setDescription(descriptionText);
            description.setBook(book);
            descriptionsService.save(description);
        }

        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/add-photo")
    public String addPhotoForm(@PathVariable("id") int id, Model model) {
        Book book = booksService.findById(id);
        if (book != null) {
            model.addAttribute("book", book);
        }

        return "books/add-photo";
    }

    @PostMapping("/{id}/add-photo")
    public String addPhoto(@PathVariable("id") int id, @RequestParam("photoUrl") String photoUrl) {
        Book book = booksService.findById(id);
        if (book != null) {
            Photo_url photo_url = new Photo_url();
            photo_url.setPhotoUrl(photoUrl);
            photo_url.setBook(book);
            photos_url_Service.save(photo_url);
        }

        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/edit-photo")
    public String editPhoto(@PathVariable("id") int id, Model model) {
        Book book = booksService.findById(id);
        Photo_url photo_url = photos_url_Service.findPhotoUrlByBookId(id);

        model.addAttribute("book", book);
        model.addAttribute("photo_url", photo_url);
        return "books/edit-photo";
    }

    @PostMapping("/{id}/update-photo")
    public String updatePhoto(@PathVariable("id") int id,
                              @RequestParam("photoUrl") String photoUrl) {
        Photo_url existingPhotoUrl = photos_url_Service.findPhotoUrlByBookId(id);
        if (existingPhotoUrl != null) {
            existingPhotoUrl.setPhotoUrl(photoUrl);
            photos_url_Service.save(existingPhotoUrl);
        }
        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/remove-photo")
    public String removePhoto(@PathVariable("id") int id) {
        Photo_url photo_url = photos_url_Service.findPhotoUrlByBookId(id);
        photos_url_Service.deleteByBookId(id);
        photos_url_Service.delete(photo_url.getPhoto_url_id());

        return "redirect:/books/" + id;
    }
}
