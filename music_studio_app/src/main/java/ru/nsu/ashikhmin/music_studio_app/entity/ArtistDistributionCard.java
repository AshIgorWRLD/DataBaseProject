package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
@Table(name = "artist_distribution_cards")
@Getter
@Setter
public class ArtistDistributionCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "artist_or_group_id",  insertable = false, updatable = false)
    private Long artistOrGroupId;

    @JsonBackReference
    @NotNull
    @ManyToOne
    private ArtistPage artistOrGroup;

    @NotNull
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "distribution_service_id", referencedColumnName = "id")
    private DistributionService distributionService;

    @Column(name = "listen_watch_amount")
    @Positive
    private Long listenWatchAmount;

    @Column(name = "monthly_listeners")
    @Positive
    private Long monthlyListeners;

    public ArtistDistributionCard(){}

    public ArtistDistributionCard(ArtistPage artistPage, DistributionService distributionService,
                                  Long listenWatchAmount, Long monthlyListeners) {
        this.artistOrGroup = artistPage;
        this.artistOrGroupId = artistPage.getId();
        this.distributionService = distributionService;
        this.listenWatchAmount = listenWatchAmount;
        this.monthlyListeners = monthlyListeners;
    }

    @Override
    public String toString(){
        return "\nArtistDistributionCard{" + "id=" + this.id + ", artist_or_group=" +
                this.artistOrGroup + ", distribution_services=" + this.distributionService +
                ", listen_watch_amount=" + this.listenWatchAmount +
                ", monthly_listeners=" + this.monthlyListeners +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        ArtistDistributionCard artistDistributionCard = (ArtistDistributionCard) o;
        return id != null && Objects.equals(id, artistDistributionCard.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

