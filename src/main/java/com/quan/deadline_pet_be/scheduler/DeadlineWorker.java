package com.quan.deadline_pet_be.scheduler;

import com.quan.deadline_pet_be.domain.Pet;
import com.quan.deadline_pet_be.domain.Task;
import com.quan.deadline_pet_be.repository.PetRepository;
import com.quan.deadline_pet_be.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeadlineWorker {
    private final TaskRepository taskRepository;
    private final PetRepository petRepository;

    // Chạy vào lúc 00:00 hàng ngày
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkDeadlines() {
        log.info("Bắt đầu quét deadline: {}", LocalDateTime.now());

        // Tìm các task chưa xong và đã quá hạn
        // (Lưu ý: Bạn nên viết thêm hàm findOverdueTasks trong TaskRepository)
        List<Task> overdueTasks = taskRepository.findAll().stream()
                .filter(t -> !"DONE".equals(t.getStatus()) && t.getDeadline().isBefore(LocalDateTime.now()))
                .toList();

        for (Task task : overdueTasks) {
            Pet pet = petRepository.findByGroupId(task.getGroup().getId()).orElse(null);
            if (pet != null && pet.getHp() > 0) {
                pet.setHp(Math.max(0, pet.getHp() - 10)); // Mỗi task trễ trừ 10 HP
                petRepository.save(pet);
                log.warn("Pet của nhóm {} bị trừ HP do trễ deadline task: {}", task.getGroup().getName(), task.getTitle());
            }
        }
    }
}