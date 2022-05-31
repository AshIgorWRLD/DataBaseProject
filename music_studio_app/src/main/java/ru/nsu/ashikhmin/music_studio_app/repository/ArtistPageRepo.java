package ru.nsu.ashikhmin.music_studio_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistPage;

public interface ArtistPageRepo extends JpaRepository<ArtistPage, Long> {

    Page<ArtistPage> findAll(Pageable pageable);
}
