package com.quan.deadline_pet_be.service.impl;

import com.quan.deadline_pet_be.domain.*;
import com.quan.deadline_pet_be.dto.request.TaskRequest;
import com.quan.deadline_pet_be.repository.*;
import com.quan.deadline_pet_be.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final PetRepository petRepository;

    @Override
    @Transactional
    public Task completeTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy task"));

        task.setStatus("DONE");

        // Logic Gamification: Tăng EXP cho Pet của nhóm
        Pet pet = petRepository.findByGroupId(task.getGroup().getId())
                .orElseThrow(() -> new RuntimeException("Nhóm chưa có Pet"));

        pet.setExp(pet.getExp() + 10); // Mỗi task xong cộng 10 EXP
        // Có thể thêm logic check level up ở đây

        petRepository.save(pet);
        return taskRepository.save(task);
    }

    public Task createTask(TaskRequest request){
        return null;
    }
}