package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "artist_distribution_cards")
@Getter
@Setter
public class ArtistDistributionCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "artist_or_group_id", referencedColumnName = "id")
    private ArtistPage artistPage;

    @NotNull
    @JsonManagedReference
    @OneToMany(mappedBy = "distributionCard", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<DistributionService> distributionServices;

    @JsonProperty("listen_watch_amount")
    @Column(name = "listen_watch_amount")
    @Positive
    private Long listenWatchAmount;

    @JsonProperty("monthly_listeners")
    @Column(name = "monthly_listeners")
    @Positive
    private Long monthlyListeners;

    public ArtistDistributionCard(){}

    public ArtistDistributionCard(ArtistPage artistPage, Set<DistributionService> distributionServices,
                                  Long listenWatchAmount, Long monthlyListeners) {
        this.artistPage = artistPage;
        this.distributionServices = distributionServices;
        this.listenWatchAmount = listenWatchAmount;
        this.monthlyListeners = monthlyListeners;
    }

    @Override
    public String toString(){
        return "\nArtistDistributionCard{" + "id=" + this.id + ", artist_or_group=" +
                this.artistPage + ", distribution_services=" + this.distributionServices +
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

