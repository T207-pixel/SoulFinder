package com.example.soulFinder.controllers;

import com.example.soulFinder.models.User;
import com.example.soulFinder.services.ParticipantService;
import com.example.soulFinder.services.ProductService;
import com.example.soulFinder.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ProductService productService;

    private final ParticipantService participantService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "registration";
        }
        String msg = userService.createUser(user);
        if (!msg.isEmpty()) {
            model.addAttribute("errorMessage", msg);
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("posts", user.getPosts());
        model.addAttribute("postsParticipant", participantService.getParticipantPosts(user.getId()));
        return "user-info";
    }

    @GetMapping("/openProfile")
    public String userProfile(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("posts", user.getPosts());
        model.addAttribute("postsParticipant", participantService.getParticipantPosts(user.getId()));
        return "user-info";
    }

}
