package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomVisitOutDto {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private Integer min;


    @Override
    public String toString(){
        return "CustomVisitOutDto={" + "clientName=" + name + ", minHoursSpent=" + min + "}";
    }
}
