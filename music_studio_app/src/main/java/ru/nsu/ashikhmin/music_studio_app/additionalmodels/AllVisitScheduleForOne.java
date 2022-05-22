package ru.nsu.ashikhmin.music_studio_app.additionalmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ashikhmin.music_studio_app.entity.Client;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AllVisitScheduleForOne {
    private Client client;
    private List<VisitScheduleWithoutClient> schedule;

    @Override
    public String toString(){
        return "AllVisitScheduleForOne{" + ", client=" + this.client +
                ", schedule=" + this.schedule +"}";
    }
}
