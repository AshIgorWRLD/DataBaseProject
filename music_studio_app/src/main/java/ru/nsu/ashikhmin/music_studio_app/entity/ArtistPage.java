package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "artist_pages")
@Getter
@Setter
public class ArtistPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "solo_artist_id", referencedColumnName = "id")
    private Artist artist;

    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @JsonBackReference
    @ManyToMany(mappedBy = "recipients")
    private List<Investment> investments;

    @JsonManagedReference
    @OneToMany(mappedBy = "artistOrGroup", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<SocialMediaStatistic> statistics;

    @JsonManagedReference
    @OneToMany(mappedBy = "artistOrGroup", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<ArtistDistributionCard> distributionCards;

    @Override
    public String toString(){
        return "ArtistPage{" + "id=" + this.id + ", artist=" + this.artist +
                ", group=" + this.group + "}";
    }

    public ArtistPage(){}

    public ArtistPage(Artist artist){
        this.artist = artist;
    }

    public ArtistPage(Group group){
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        ArtistPage artistPage = (ArtistPage) o;
        return id != null && Objects.equals(id, artistPage.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
