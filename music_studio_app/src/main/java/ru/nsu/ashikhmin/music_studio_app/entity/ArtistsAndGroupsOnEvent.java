package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "artists_and_groups_on_event")
@Getter
@Setter
public class ArtistsAndGroupsOnEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "artist_or_group_id", referencedColumnName = "id")
    private ArtistPage artistPage;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @NotNull
    @JsonProperty("performance_time")
    @Column(name = "performance_time")
    private Time performanceTime;

    @NotNull
    @Min(value = 100)
    @Max(value = 99999999)
    private Long income;

    public ArtistsAndGroupsOnEvent(){}

    public ArtistsAndGroupsOnEvent(ArtistPage artistPage, Event event,
                                   Time performanceTime, Long income) {
        this.artistPage = artistPage;
        this.event = event;
        this.performanceTime = performanceTime;
        this.income = income;
    }

    @Override
    public String toString(){
        return "\nArtistsAndGroupsOnEvent{" + "id=" + this.id +
                ", artist_or_group=" + this.artistPage +
                ", event=" + this.event +
                ", performance_time=" + this.performanceTime +
                ", income=" + this.income + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        ArtistsAndGroupsOnEvent artistsAndGroupsOnEvent = (ArtistsAndGroupsOnEvent) o;
        return id != null && Objects.equals(id, artistsAndGroupsOnEvent.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
