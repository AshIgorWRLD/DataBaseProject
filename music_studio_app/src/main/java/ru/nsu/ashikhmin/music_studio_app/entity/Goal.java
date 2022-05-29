package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "goals")
@Getter
@Setter
public class Goal {

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
    @JoinColumn(name = "progress_id", referencedColumnName = "id")
    private Progress progress;

    @NotNull
    @Column(name = "progress_percentage")
    private Double progressPercentage;

    @NotNull
    @NotBlank
    private String statement;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private Date deadline;

    @NotBlank
    private String resources;

    public Goal(){}

    public Goal(ArtistPage artistPage, Progress progress, Double progressPercentage,
                String statement, String type, Date deadline, String resources) {
        this.artistPage = artistPage;
        this.progress = progress;
        this.progressPercentage = progressPercentage;
        this.statement = statement;
        this.type = type;
        this.deadline = deadline;
        this.resources = resources;
    }

    public Goal(ArtistPage artistPage, Progress progress, Double progressPercentage,
                String statement, String type, Date deadline) {
        this.artistPage = artistPage;
        this.progress = progress;
        this.progressPercentage = progressPercentage;
        this.statement = statement;
        this.type = type;
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return "\nGoal{" + "id=" + this.id + ", artist_or_group=" + this.artistPage +
                ", progress=" + this.progress +
                ", progress_percentage=" + this.progressPercentage +
                ", statement=" + this.statement +
                ", type=" + this.type +
                ", deadline=" + this.deadline + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        Goal goal = (Goal) o;
        return id != null && Objects.equals(id, goal.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
