package com.quan.deadline_pet_be.repository;

import com.quan.deadline_pet_be.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    // Lấy thông tin thú cưng dựa trên ID của nhóm (Vì 1 nhóm chỉ có 1 thú cưng)
    Optional<Pet> findByGroupId(Long groupId);
}