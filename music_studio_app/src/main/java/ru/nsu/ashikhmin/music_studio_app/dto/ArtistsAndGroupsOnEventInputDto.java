package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

import java.sql.Time;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistsAndGroupsOnEventInputDto {
    private Long artistPageId;
    private Long eventId;
    private Time performanceTime;
    private Long income;

    @Override
    public String toString(){
        return "\nArtistsAndGroupsOnEvent{" +
                ", artist_or_group_id=" + this.artistPageId +
                ", event_id=" + this.eventId +
                ", performance_time=" + this.performanceTime +
                ", income=" + this.income + "}";
    }
}
