package com.example.soulFinder.models;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
//    @Pattern(regexp = "^[a-zA-Z]+\\s[a-zA-Z]+\\s[a-zA-Z]+$", message = "Введите с пробелами три слова")
    private String title;

    @Column(name = "age")
    @Min(0)
    @Max(130)
    private Integer age;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "date_of_disappearance")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_of_disappearance;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "post")
    private List<Image> images = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user_p")
    private List<Participant> participants = new ArrayList<>();

    private Long previewImageId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public void addImageToProduct(Image image) {
        image.setPost(this);
        images.add(image);
    }

//    public void addParticipantToPost(User user) {
//        participants.add(user);
//    }
}
