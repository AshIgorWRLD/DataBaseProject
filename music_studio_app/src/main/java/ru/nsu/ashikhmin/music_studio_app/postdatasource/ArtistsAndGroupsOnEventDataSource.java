package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Time;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistsAndGroupsOnEventDataSource {
    @JsonProperty("artist_or_group_id")
    private Long artistPageId;
    @JsonProperty("event_id")
    private Long eventId;
    @JsonProperty("performance_time")
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
