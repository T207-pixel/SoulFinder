package com.example.soulFinder.services;

import com.example.soulFinder.models.Image;
import com.example.soulFinder.models.Post;
import com.example.soulFinder.models.User;
import com.example.soulFinder.repositories.ProductRepository;
import com.example.soulFinder.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public List<Post> listProducts(String title) {
        if (title != null) {
            List<Post> neededPosts = new ArrayList<>();
            for (Post post : productRepository.findAll()) {
                if (post.getTitle().contains(title)) {
                    neededPosts.add(post);
                }
            }
            return neededPosts;
        }
        return productRepository.findAll();
    }

    public void saveProduct(Principal principal, Post post, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        post.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            post.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            post.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            post.addImageToProduct(image3);
        }
        log.info("Saving new Product. Title: {}; Author email {}", post.getTitle(), post.getUser().getEmail());
        Post postFromDb = productRepository.save(post);
        postFromDb.setPreviewImageId(postFromDb.getImages().get(0).getId());
        productRepository.save(post);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Post getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

}
