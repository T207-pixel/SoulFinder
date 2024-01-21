package com.example.soulFinder.controllers;

import com.example.soulFinder.models.Post;
import com.example.soulFinder.services.ParticipantService;
import com.example.soulFinder.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ParticipantService participantService;

    @GetMapping("/")
    public String posts(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("posts", productService.listProducts(title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "posts";
    }

    @GetMapping("/post/{id}")
    public String postInfo(@PathVariable Long id, Model model) {
        Post post = productService.getProductById(id);
        model.addAttribute("post", productService.getProductById(id));
        model.addAttribute("images", post.getImages());
        model.addAttribute("users", participantService.getParticipants(id));
        return "post-info";
    }

    @PostMapping("/post/{id}/take_part")
    public String takePart(@PathVariable Long id,Principal principal) {
        participantService.addParticipant(principal, id);
        return "redirect:/post/{id}";
    }

    @PostMapping("/post/create")
    public String createPost(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3, Post post, Principal principal) throws IOException {
        productService.saveProduct(principal, post, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping("/post/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
