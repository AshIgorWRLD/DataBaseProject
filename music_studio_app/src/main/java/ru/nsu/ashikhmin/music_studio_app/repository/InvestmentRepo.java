package ru.nsu.ashikhmin.music_studio_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ashikhmin.music_studio_app.entity.AdvertisementCompany;
import ru.nsu.ashikhmin.music_studio_app.entity.Investment;

public interface InvestmentRepo extends JpaRepository<Investment, Long> {

    Page<Investment> findAll(Pageable pageable);
}
