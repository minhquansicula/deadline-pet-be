package com.quan.deadline_pet_be.controller.task;

import com.quan.deadline_pet_be.domain.Task;
import com.quan.deadline_pet_be.dto.request.TaskRequest;
import com.quan.deadline_pet_be.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // API 1: Tạo Task mới
    @PostMapping
    public ResponseEntity<Task> createNewTask(@RequestBody TaskRequest request) {
        Task createdTask = taskService.createTask(request);
        return ResponseEntity.ok(createdTask);
    }

    // API 2: Đánh dấu hoàn thành Task (Gamification trigger)
    @PutMapping("/{taskId}/complete")
    public ResponseEntity<String> completeTaskAndGainExp(@PathVariable Long taskId) {
        taskService.completeTask(taskId);
        return ResponseEntity.ok("Chúc mừng! Hoàn thành công việc, Thú cưng đã được cộng thêm 20 EXP 🚀");
    }
}