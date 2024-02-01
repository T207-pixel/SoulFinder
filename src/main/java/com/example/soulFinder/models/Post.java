package com.example.soulFinder.models;

import com.example.soulFinder.models.customValidators.PastOrPresentDate;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.postgresql.geometric.PGpoint;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @Column(name = "isPostCheckedByAdmin")
    private Boolean isPostCheckedByAdmin = false;

    @Column(name = "title")
    @NotEmpty(message = "Имя Фамилия Отчество  не должно быть пустыми")
    @Size(min = 5, max = 100, message = "Введите понятное фио")
    private String title;

    @Column(name = "age")
    @Min(value = 0, message = "Возраст не может быть отрицательным")
    @Max(value = 130, message = "Возраст не может быть больше 130")
    @NotNull(message = "Возраст должен быть указан")
    private Integer age;

    @NotEmpty(message = "Описание не должно быть пустым")
    @Column(name = "description", columnDefinition = "text", length = 255)
    private String description;

    @NotEmpty(message = "локация не должна быть пустой")
    @Column(name = "location")
    @Size(max = 250, message = "Описание не должно быть более 250 символов")
    private String location;

    @NotNull(message = "Введите координаты")
    private PGpoint coordinates;

    @Column(name = "date_of_disappearance")
    @Temporal(TemporalType.DATE)
    @PastOrPresentDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    @NotNull(message = "Введите дату")
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

    public void addCoordinates(PGpoint point) {
        this.coordinates = point;
    }

}
