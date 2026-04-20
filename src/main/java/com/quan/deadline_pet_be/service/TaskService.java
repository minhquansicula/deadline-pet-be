package com.quan.deadline_pet_be.service;
import com.quan.deadline_pet_be.domain.Task;
import com.quan.deadline_pet_be.dto.request.TaskRequest;

public interface TaskService {
    Task createTask(TaskRequest request);
    Task completeTask(Long taskId);
}