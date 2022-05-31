package ru.nsu.ashikhmin.music_studio_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ashikhmin.music_studio_app.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);
}
