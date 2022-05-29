package ru.nsu.ashikhmin.music_studio_app.dto;

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
public class VisitScheduleInputDto {

    private Long clientId;
    private Date visitDate;
    private Time timing;
    private Integer lengthOfVisit;
    private String type;

    @Override
    public String toString(){
        return "VisitScheduleDataSource{" + "client_id=" + this.clientId +
                ", visit_date=" + this.visitDate + ", timing=" + this.timing +
                ", length_of_visit=" + this.lengthOfVisit + ", type=" + this.type +"}";
    }
}
