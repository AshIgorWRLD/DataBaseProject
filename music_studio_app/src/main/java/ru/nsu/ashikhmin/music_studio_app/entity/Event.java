package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "events")
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "event_name")
    private String eventName;

    @NotNull
    @Column(name = "audience_amount")
    @Min(value = 10)
    private Integer audienceAmount;

    @NotNull
    @NotBlank
    private String point;

    @NotNull
    @Column(name = "event_date")
    private Date eventDate;

    @NotNull
    private Time timing;

    @NotNull
    @Column(name = "event_rank")
    private Integer eventRank;

    public Event(){}

    public Event(String eventName, Integer audienceAmount, String point, Date eventDate,
                 Time timing, Integer eventRank) {
        this.eventName = eventName;
        this.audienceAmount = audienceAmount;
        this.point = point;
        this.eventDate = eventDate;
        this.timing = timing;
        this.eventRank = eventRank;
    }

    @Override
    public String toString(){
        return "\nUser{" + "id=" + this.id + ", event_name=" + this.eventName +
                ", audience_amount=" + this.audienceAmount + ", point=" + this.point +
                ", event_date=" + this.eventDate + ", timing=" + this.timing +
                ", event_rank=" + this.eventRank+ "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        Event event = (Event) o;
        return id != null && Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
