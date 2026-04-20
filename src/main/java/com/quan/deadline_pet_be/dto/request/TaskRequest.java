package com.quan.deadline_pet_be.dto.request;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private LocalDateTime deadline;
    private Long groupId;
    private Long assigneeId;
}