package com.quan.deadline_pet_be.domain;

import com.quan.deadline_pet_be.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teams") // Tương tự, tránh từ khóa 'groups' trong SQL
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, length = 10)
    private String inviteCode; // Mã để mời bạn bè vào nhóm
}