package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistPage;

import java.sql.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoalDataSource {
    @JsonProperty("artist_or_group_id")
    private Long artistPageId;
    @JsonProperty("progress_id")
    private Long progressId;
    @JsonProperty("progress_percentage")
    private Double progressPercentage;
    private String statement;
    private String type;
    private Date deadline;
    private String resources;

    @Override
    public String toString(){
        return "\nGoal{" + "artist_or_group_id=" + this.artistPageId +
                ", progress_id=" + this.progressId +
                ", progress_percentage=" + this.progressPercentage +
                ", statement=" + this.statement +
                ", type=" + this.type +
                ", deadline=" + this.deadline + "}";
    }
}
