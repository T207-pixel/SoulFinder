package com.example.soulFinder.controllers;

import com.example.soulFinder.models.Post;
import com.example.soulFinder.services.ParticipantService;
import com.example.soulFinder.services.PostService;
import lombok.RequiredArgsConstructor;
import org.postgresql.geometric.PGpoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final ParticipantService participantService;

    private final String yandexMapsApiKey;

    @GetMapping("/")
    public String posts(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("posts", postService.listProducts(title));
        model.addAttribute("user", postService.getUserByPrincipal(principal));
        return "posts";
    }

    @GetMapping("/post/{id}")
    public String postInfo(@PathVariable Long id, Model model, Principal principal) {
        Post post = postService.getProductById(id);
        List<Double> coordinatesList = pgpointToList(post.getCoordinates());
        model.addAttribute("coordinates", coordinatesList);
        model.addAttribute("user", postService.getUserByPrincipal(principal));
        model.addAttribute("post", postService.getProductById(id));
        model.addAttribute("images", post.getImages());
        model.addAttribute("users", participantService.getParticipants(id));
        model.addAttribute("yandexMapsApiKey", yandexMapsApiKey);
        return "post-info";
    }

    @PostMapping("/post/{id}/take_part")
    public String takePart(@PathVariable Long id,Principal principal) {
        participantService.addParticipant(principal, id);
        return "redirect:/post/{id}";
    }

    @GetMapping("/post/create")
    public String createPostPage(Principal principal, Model model) {
        model.addAttribute("user", postService.getUserByPrincipal(principal));
        return "create-post-page";
    }

    @PostMapping("/post/create")
    public String createPost(@RequestParam("coordinates") String coordinates, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3, @Valid Post post, BindingResult bindingResult, Principal principal, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("posts", postService.listProducts(""));
            model.addAttribute("user", postService.getUserByPrincipal(principal));
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("errorPost", post);
            return "create-post-page";
        }
        if (!postService.saveProduct(principal, post, file1, file2, file3, coordinates)) {
            System.out.println("I'm here2");
            model.addAttribute("user", postService.getUserByPrincipal(principal));
            model.addAttribute("overFlow", "К сожалению вы не можете добавить более пяти постов");
            model.addAttribute("errorPost", post);
            return "create-post-page";
        }
        return "redirect:/";
    }

    @PostMapping("/post/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deleteProduct(id);
        return "redirect:/";
    }

    @PostMapping("/post/{id}/approve")
    public String approvePost(@PathVariable Long id) {
        postService.approvePost(id);
        return "redirect:/";
    }

    public List<Double> pgpointToList(PGpoint point) {
        return Arrays.asList(point.x, point.y);
    }
}
