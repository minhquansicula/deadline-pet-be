package com.quan.deadline_pet_be.domain;

import com.quan.deadline_pet_be.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Builder.Default
    private Integer level = 1;

    @Builder.Default
    private Integer exp = 0;

    @Builder.Default
    private Integer hp = 100; // Máu mặc định là 100

    @Builder.Default
    private Integer mood = 100; // Chỉ số vui vẻ

    // Mỗi nhóm chỉ có đúng 1 con thú cưng
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false, unique = true)
    private Group group;
}