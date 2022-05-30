package ru.nsu.ashikhmin.music_studio_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ashikhmin.music_studio_app.entity.AdvertisementCompany;
import ru.nsu.ashikhmin.music_studio_app.entity.VisitSchedule;

import java.util.List;

public interface VisitScheduleRepo extends JpaRepository<VisitSchedule, Long> {

    Page<VisitSchedule> findAll(Pageable pageable);

    List<VisitSchedule> findByClientId(Long id);
}
