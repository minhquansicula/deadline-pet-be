package com.quan.deadline_pet_be.repository;

import com.quan.deadline_pet_be.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    // Tìm kiếm nhóm thông qua mã mời (Invite Code) để user join vào nhóm
    Optional<Group> findByInviteCode(String inviteCode);
}