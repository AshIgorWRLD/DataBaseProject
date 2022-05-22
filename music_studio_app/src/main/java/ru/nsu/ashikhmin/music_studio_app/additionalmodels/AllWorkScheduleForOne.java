package ru.nsu.ashikhmin.music_studio_app.additionalmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ashikhmin.music_studio_app.entity.Employee;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AllWorkScheduleForOne {
    private Employee employee;
    private List<WorkScheduleWithoutEmployee> schedule;

    @Override
    public String toString(){
        return "AllWorkScheduleForOne{" + ", employee=" + this.employee +
                ", schedule=" + this.schedule +"}";
    }
}
