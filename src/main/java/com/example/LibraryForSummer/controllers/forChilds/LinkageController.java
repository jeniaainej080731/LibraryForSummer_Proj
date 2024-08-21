package com.example.LibraryForSummer.controllers.forChilds;

import com.example.LibraryForSummer.models.Childs.Linkage;
import com.example.LibraryForSummer.services.forChilds.LinkageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/linkage")
public class LinkageController {
    private final LinkageService linkageService;

    @Autowired
    public LinkageController(LinkageService linkageService) {
        this.linkageService = linkageService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("linkage", linkageService.findAll());
        return "linkage/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("linkage", linkageService.findById(id));
        return "linkage/show";
    }

    @GetMapping("/new")
    public String newService(@ModelAttribute("linkage") Linkage linkage) {
        return "linkage/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("linkage") @Valid Linkage linkage, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "linkage/new";
        }

        linkageService.save(linkage);
        return "redirect:/linkage";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("linkage", linkageService.findById(id));
        return "linkage/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("linkage") @Valid Linkage linkage, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "linkage/edit";
        }

        linkageService.update(id, linkage);
        return "redirect:/linkage";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        linkageService.delete(id);
        return "redirect:/linkage";
    }
}
