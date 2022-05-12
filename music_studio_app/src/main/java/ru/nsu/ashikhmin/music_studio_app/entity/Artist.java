package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "artists")
@Getter
@Setter
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @JsonProperty("group_id")
    @Column(name = "group_id", insertable = false, updatable = false)
    private Long groupId;

    @JsonBackReference
    @ManyToOne()
    private Group group;

    @NotBlank
    @NotNull
    @Column(name = "stage_name")
    private String stageName;

    @NotBlank
    @NotNull
    private String genre;

    @NotNull
    @Column(name = "creation_date")
    private Date creationDate;

    public Artist(){}

    public Artist(Client client, Group group, String stageName, String genre,
                  Date creationDate){
        this.client = client;
        this.group = group;
        this.stageName = stageName;
        this.genre = genre;
        this.creationDate = creationDate;
    }

    public Artist(Client client, String stageName, String genre,
                  Date creationDate){
        this.client = client;
        this.stageName = stageName;
        this.genre = genre;
        this.creationDate = creationDate;
    }

    @Override
    public String toString(){
        return "Artist{" + "id=" + this.id + ", client=" + this.client +
                ", group=" + this.group + ", stage_name=" + this.stageName
                + ", genre=" + this.genre + ", creation_date=" + this.creationDate + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        Artist artist = (Artist) o;
        return id != null && Objects.equals(id, artist.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
