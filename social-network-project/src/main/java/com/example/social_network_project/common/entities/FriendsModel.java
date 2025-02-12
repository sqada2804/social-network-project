package com.example.social_network_project.common.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "friends")
public class FriendsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendId;

    @Column(name = "created_at")
    private Date createdDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_user_id", referencedColumnName = "userId")
    UserModel firstUser;

    @OneToOne
    @JoinColumn(name = "second_user_id", referencedColumnName = "userId")
    UserModel secondUser;
}
