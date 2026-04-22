package com.quan.deadline_pet_be.service;

import com.quan.deadline_pet_be.domain.Pet;

public interface PetService {
    Pet getPetByGroup(Long groupId);
    void addExp(Long groupId, Integer amount);
}