package com.example.soulFinder.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "participants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_p;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post_p;
}
