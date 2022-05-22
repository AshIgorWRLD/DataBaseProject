package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Data
@Getter
@Setter
@AllArgsConstructor
public class WorkScheduleDataSource {

    @JsonProperty("employee_id")
    private Long employeeId;
    @JsonProperty("week_day")
    private Integer weekDay;
    @JsonProperty("time_to_come")
    private Time timeToCome;
    @JsonProperty("work_length")
    private Integer workLength;

    @Override
    public String toString(){
        return "WorkScheduleDataSource{" + "employee_id=" + this.employeeId +
                ", week_day=" + this.weekDay + ", time_to_come=" + this.timeToCome +
                ", work_length=" + this.workLength +"}";
    }
}
