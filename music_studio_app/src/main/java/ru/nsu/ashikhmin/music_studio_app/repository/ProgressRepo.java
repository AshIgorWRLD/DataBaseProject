package ru.nsu.ashikhmin.music_studio_app.repository;

import liquibase.pro.packaged.P;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ashikhmin.music_studio_app.entity.AdvertisementCompany;
import ru.nsu.ashikhmin.music_studio_app.entity.Progress;

import java.util.List;
import java.util.Set;

public interface ProgressRepo extends JpaRepository<Progress, Long> {

    Page<Progress> findAll(Pageable pageable);

    Page<Progress> findByArtistPageIdIn(Set<Long> ids, Pageable pageable);
}
