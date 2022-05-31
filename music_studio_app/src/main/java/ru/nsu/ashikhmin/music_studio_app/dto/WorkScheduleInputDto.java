package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Data
@Getter
@Setter
@AllArgsConstructor
public class WorkScheduleInputDto {

    private Long employeeId;
    private Integer weekDay;
    private Time timeToCome;
    private Integer workLength;

    @Override
    public String toString(){
        return "WorkScheduleDataSource{" + "employee_id=" + this.employeeId +
                ", week_day=" + this.weekDay + ", time_to_come=" + this.timeToCome +
                ", work_length=" + this.workLength +"}";
    }
}
