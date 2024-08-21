package com.example.LibraryForSummer.controllers.unique;

import com.example.LibraryForSummer.models.unique.Utilizer;
import com.example.LibraryForSummer.services.unique.UserService;
import com.example.LibraryForSummer.util.UserValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping
    public String home(HttpSession session, Model model) {
        Utilizer user = (Utilizer) session.getAttribute("user");
        boolean isLoggedIn = user != null;
        model.addAttribute("isLoggedIn", isLoggedIn);
        return "home/mainpage";
    }

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    @PostMapping("/login")
    public String loginUser(Utilizer user, HttpSession session, Model model) {
        Optional<Utilizer> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent() && userService.checkPassword(existingUser.get(), user.getPassword())) {
            session.setAttribute("user", existingUser.get());
            return "redirect:/home";
        }
        model.addAttribute("error", "Invalid email or password.");
        return "home/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new Utilizer());
        return "home/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid Utilizer user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        model.addAttribute("user", user);

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "home/register";
        }

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "Email is already registered.");
            return "home/register";
        }

        userService.saveUser(user);
        return "redirect:/home/login";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/home/login";
    }
}
