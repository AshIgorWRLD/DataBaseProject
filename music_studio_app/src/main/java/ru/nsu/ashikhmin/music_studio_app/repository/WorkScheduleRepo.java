package ru.nsu.ashikhmin.music_studio_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ashikhmin.music_studio_app.entity.AdvertisementCompany;
import ru.nsu.ashikhmin.music_studio_app.entity.WorkSchedule;

import java.util.List;

public interface WorkScheduleRepo extends JpaRepository<WorkSchedule, Long> {

    Page<WorkSchedule> findAll(Pageable pageable);

    List<WorkSchedule> findByEmployeeId(Long id);
}
