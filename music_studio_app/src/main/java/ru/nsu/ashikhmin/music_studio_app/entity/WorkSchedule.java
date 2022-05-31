package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "work_schedule")
@Getter
@Setter
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @Column(name = "week_day")
    private Integer weekDay;

    @Column(name = "time_to_come")
    private Time timeToCome;

    @Column(name = "work_length")
    private Integer workLength;

    public WorkSchedule(){}

    public WorkSchedule(Employee employees, Integer weekDay,
                        Time timeToCome, Integer workLength) {
        this.employee = employees;
        this.weekDay = weekDay;
        this.timeToCome = timeToCome;
        this.workLength = workLength;
    }

    @Override
    public String toString(){
        return "WorkSchedule{" + "id=" + this.id + ", employee=" + this.employee +
                ", week_day=" + this.weekDay + ", time_to_come=" + timeToCome +
                ", work_length=" + this.workLength + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        WorkSchedule workSchedule = (WorkSchedule) o;
        return id != null && Objects.equals(id, workSchedule.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
