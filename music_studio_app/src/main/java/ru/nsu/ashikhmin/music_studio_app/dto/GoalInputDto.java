package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

import java.sql.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoalInputDto {
    private Long artistPageId;
    private Long progressId;
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
