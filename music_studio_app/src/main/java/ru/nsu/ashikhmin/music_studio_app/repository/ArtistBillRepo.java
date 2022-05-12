package ru.nsu.ashikhmin.music_studio_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistBill;

public interface ArtistBillRepo extends JpaRepository<ArtistBill, Long> {
}
