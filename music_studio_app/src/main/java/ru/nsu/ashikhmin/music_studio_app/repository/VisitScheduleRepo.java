package ru.nsu.ashikhmin.music_studio_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ashikhmin.music_studio_app.entity.VisitSchedule;

import java.util.List;

public interface VisitScheduleRepo extends JpaRepository<VisitSchedule, Long> {

    List<VisitSchedule> findByClientId(Long id);
}
