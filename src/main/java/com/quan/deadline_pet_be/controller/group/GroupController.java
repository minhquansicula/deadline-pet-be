package com.quan.deadline_pet_be.controller.group;

import com.quan.deadline_pet_be.domain.Group;
import com.quan.deadline_pet_be.domain.Pet;
import com.quan.deadline_pet_be.repository.GroupRepository;
import com.quan.deadline_pet_be.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupRepository groupRepository;
    private final PetRepository petRepository;

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestParam String name) {
        // Tạo nhóm với mã mời ngẫu nhiên 6 ký tự
        Group group = Group.builder()
                .name(name)
                .inviteCode(UUID.randomUUID().toString().substring(0, 6).toUpperCase())
                .build();
        Group savedGroup = groupRepository.save(group);

        // Tự động tạo 1 Pet cho nhóm mới
        Pet pet = Pet.builder()
                .name("Thú cưng của " + name)
                .group(savedGroup)
                .hp(100).exp(0).level(1).mood(100)
                .build();
        petRepository.save(pet);

        return ResponseEntity.ok(savedGroup);
    }
}