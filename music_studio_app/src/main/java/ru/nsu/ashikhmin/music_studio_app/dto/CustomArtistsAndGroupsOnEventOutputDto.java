package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomArtistsAndGroupsOnEventOutputDto {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String stageName;
    private String eventName;
    private Timestamp performanceTime;
    private Long income;
}
