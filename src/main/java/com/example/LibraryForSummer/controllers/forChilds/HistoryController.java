package com.example.LibraryForSummer.controllers.forChilds;

import com.example.LibraryForSummer.models.Childs.History;
import com.example.LibraryForSummer.services.forChilds.HistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("histories", historyService.findAll());
        return "history/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("history", historyService.findById(id));
        return "history/show";
    }

    @GetMapping("/new")
    public String newHistory(@ModelAttribute("history") History history) {
        return "history/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("history") @Valid History history, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "history/new";
        }

        historyService.save(history);
        return "redirect:/history";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("history", historyService.findById(id));
        return "history/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("history") @Valid History history, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "history/edit";
        }

        historyService.update(id, history);
        return "redirect:/history";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        historyService.delete(id);
        return "redirect:/history";
    }
}