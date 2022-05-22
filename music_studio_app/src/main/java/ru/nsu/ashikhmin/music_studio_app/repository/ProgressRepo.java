package ru.nsu.ashikhmin.music_studio_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ashikhmin.music_studio_app.entity.Progress;

import java.util.List;
import java.util.Set;

public interface ProgressRepo extends JpaRepository<Progress, Long> {

    List<Progress> findByArtistPageIdIn(Set<Long> ids);
}
