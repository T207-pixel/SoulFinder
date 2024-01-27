package com.example.soulFinder.services;

import com.example.soulFinder.models.Participant;
import com.example.soulFinder.models.Post;
import com.example.soulFinder.models.User;
import com.example.soulFinder.repositories.ParticipantRepository;
import com.example.soulFinder.repositories.PostRepository;
import com.example.soulFinder.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    private final PostService postService;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    public void addParticipant(Principal principal, Long postId) {
        Participant participant = new Participant();
        Post post = postService.getProductById(postId);
        User user = postService.getUserByPrincipal(principal);

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
        return postRepository.findAllById(postIds);
    }

}
