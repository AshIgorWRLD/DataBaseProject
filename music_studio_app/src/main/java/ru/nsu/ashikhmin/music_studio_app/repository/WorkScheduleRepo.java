package ru.nsu.ashikhmin.music_studio_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ashikhmin.music_studio_app.entity.WorkSchedule;

import java.util.List;

public interface WorkScheduleRepo extends JpaRepository<WorkSchedule, Long> {

    List<WorkSchedule> findByEmployeeId(Long id);
}
