package ru.nsu.ashikhmin.music_studio_app.additionalmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Data
@Getter
@Setter
@AllArgsConstructor
public class WorkScheduleWithoutEmployee {
    @JsonProperty("week_day")
    private Integer weekDay;
    @JsonProperty("time_to_come")
    private Time timeToCome;
    @JsonProperty("work_length")
    private Integer workLength;

    @Override
    public String toString(){
        return "WorkScheduleWithoutEmployee{" + ", week_day=" + this.weekDay +
                ", time_to_come=" + this.timeToCome +
                ", work_length=" + this.workLength +"}";
    }
}
