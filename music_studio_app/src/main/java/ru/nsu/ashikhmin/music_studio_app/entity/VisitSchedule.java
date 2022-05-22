package ru.nsu.ashikhmin.music_studio_app.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "visit_schedule")
@Getter
@Setter
public class VisitSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @NotNull
    private Date visitDate;

    @NotNull
    private Time timing;

    @NotNull
    @Positive
    @Max(value = 480)
    private Integer lengthOfVisit;

    @NotNull
    @NotBlank
    private String type;

    public VisitSchedule(){}

    public VisitSchedule(Client client, Date visitDate, Time timing,
                         Integer lengthOfVisit, String type) {
        this.client = client;
        this.visitDate = visitDate;
        this.timing = timing;
        this.lengthOfVisit = lengthOfVisit;
        this.type = type;
    }

    @Override
    public String toString(){
        return "VisitSchedule{" + "id=" + this.id + ", client=" + this.client +
                ", visit_date=" + this.visitDate + ", timing=" + this.timing +
                ", length_of_visit=" + this.lengthOfVisit + ", type=" + this.type +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        VisitSchedule visitSchedule = (VisitSchedule) o;
        return id != null && Objects.equals(id, visitSchedule.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
