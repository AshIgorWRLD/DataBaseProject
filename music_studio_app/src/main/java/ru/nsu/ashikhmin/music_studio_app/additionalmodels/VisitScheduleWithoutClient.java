package ru.nsu.ashikhmin.music_studio_app.additionalmodels;

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
public class VisitScheduleWithoutClient {

    @JsonProperty("visit_date")
    private Date visitDate;
    private Time timing;
    @JsonProperty("length_of_visit")
    private Integer lengthOfVisit;
    private String type;

    @Override
    public String toString(){
        return "VisitScheduleWithoutClient{" + "visit_date=" + this.visitDate +
                ", timing=" + this.timing + ", length_of_visit=" +
                this.lengthOfVisit + ", type=" + this.type +"}";
    }
}
