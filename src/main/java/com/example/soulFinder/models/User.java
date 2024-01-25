package com.example.soulFinder.models;

import com.example.soulFinder.models.enums.Role;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "неверный формат почты")
    private String email;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "неверный формат телефона")
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "name")
    @Size(min = 5, max = 100, message = "Имя должно иметь 3 - 25 символов")
    private String name;

    @Column(name = "active")
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image avatar;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&!_\\-+=]).{6,}$", message = "пароль должен содержать следующие символы: " +
            "1) Маленькую латинскую букву " +
            "2) Большую латинскую букву " +
            "3) Число " +
            "4) Спец символ " +
            "5) Быть не менее 6 символов ")
    @Column(name = "password", length = 100)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch =FetchType.EAGER, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    private LocalDateTime dateOfCreated;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post_p")
    private List<Participant> participants = new ArrayList<>();

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

//    public void addPostParticipant(Post post) {
//        participant_posts.add(post);
//    }
}
