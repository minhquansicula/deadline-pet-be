package com.quan.deadline_pet_be.service.impl;

import com.quan.deadline_pet_be.domain.Pet;
import com.quan.deadline_pet_be.repository.PetRepository;
import com.quan.deadline_pet_be.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    @Override
    public Pet getPetByGroup(Long groupId) {
        return petRepository.findByGroupId(groupId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thú cưng của nhóm này"));
    }

    @Override
    @Transactional
    public void addExp(Long groupId, Integer amount) {
        Pet pet = getPetByGroup(groupId);
        pet.setExp(pet.getExp() + amount);

        // Công thức tiến hóa: Level kế tiếp cần Level * 100 EXP
        int nextLevelExp = pet.getLevel() * 100;
        if (pet.getExp() >= nextLevelExp) {
            pet.setExp(pet.getExp() - nextLevelExp);
            pet.setLevel(pet.getLevel() + 1);
            pet.setHp(100); // Hồi máu khi lên cấp
        }
        petRepository.save(pet);
    }
}