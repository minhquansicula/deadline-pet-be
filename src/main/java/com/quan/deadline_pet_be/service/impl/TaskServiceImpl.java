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
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Override
    @Transactional
    public Task createTask(TaskRequest request) {
        // Tìm Group từ Database
        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Nhóm"));

        // Tìm User được gán việc (Assignee)
        User assignee = null;
        if (request.getAssigneeId() != null) {
            assignee = userRepository.findById(request.getAssigneeId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Người dùng"));
        }

        // Tạo đối tượng Task mới
        Task newTask = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .deadline(request.getDeadline())
                .status("TODO") // Mặc định khi tạo là TODO
                .group(group)
                .assignee(assignee)
                .build();

        return taskRepository.save(newTask);
    }

    @Override
    @Transactional
    public Task completeTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy công việc"));

        if ("DONE".equals(task.getStatus())) {
            throw new RuntimeException("Công việc này đã hoàn thành rồi!");
        }

        task.setStatus("DONE");

        // Logic Gamification: Tăng 20 EXP cho Pet của nhóm
        Pet pet = petRepository.findByGroupId(task.getGroup().getId())
                .orElseThrow(() -> new RuntimeException("Nhóm này chưa có Thú cưng"));

        pet.setExp(pet.getExp() + 20);

        // Cập nhật vào DB
        petRepository.save(pet);
        return taskRepository.save(task);
    }
}