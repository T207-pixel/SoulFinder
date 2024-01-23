package com.example.soulFinder.services;

import com.example.soulFinder.models.Participant;
import com.example.soulFinder.models.Post;
import com.example.soulFinder.models.User;
import com.example.soulFinder.repositories.ParticipantRepository;
import com.example.soulFinder.repositories.ProductRepository;
import com.example.soulFinder.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    private final ProductService productService;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public void addParticipant(Principal principal, Long postId) {
        Participant participant = new Participant();
        Post post = productService.getProductById(postId);
        User user = productService.getUserByPrincipal(principal);

        participant.setUser_p(user);
        participant.setPost_p(post);

        participantRepository.save(participant);
    }

    public List<User> getParticipants(Long postId) {
        List<Long> userIds = participantRepository.findUsersByPostId(postId);
        return userRepository.findAllById(userIds);
    }

    public List<Post> getParticipantPosts(Long participantId) {
        List<Long> postIds = participantRepository.findPostsByUserId(participantId);
        return productRepository.findAllById(postIds);
    }

}
