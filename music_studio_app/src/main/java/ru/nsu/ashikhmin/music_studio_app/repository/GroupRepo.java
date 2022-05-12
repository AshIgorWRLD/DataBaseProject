package ru.nsu.ashikhmin.music_studio_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ashikhmin.music_studio_app.entity.Group;

public interface GroupRepo extends JpaRepository<Group, Long> {

    Group findByName(String name);
}
