package ru.nsu.ashikhmin.music_studio_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ashikhmin.music_studio_app.entity.AdvertisementCompany;

import java.util.List;

public interface AdvertisementCompanyRepo extends JpaRepository<AdvertisementCompany, Long> {

    Page<AdvertisementCompany> findAll(Pageable pageable);
}
