package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
@Table(name = "social_media_statistic")
@Getter
@Setter
public class SocialMediaStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "artist_or_group_id", insertable = false, updatable = false)
    private Long artistOrGroupId;

    @JsonBackReference
    @NotNull
    @ManyToOne
    private ArtistPage artistOrGroup;

    @NotNull
    @NotBlank
    @Column(name = "social_network")
    private String socialNetwork;

    @NotNull
    @Positive
    @Column(name = "subscribers_amount")
    private Long subscribersAmount;

    @NotNull
    @Positive
    @Column(name = "live_subscribers")
    private Long liveSubscribers;

    public SocialMediaStatistic(){}

    public SocialMediaStatistic(ArtistPage artistPage, String socialNetwork,
                                Long subscribersAmount, Long liveSubscribers) {
        this.artistOrGroup = artistPage;
        this.artistOrGroupId = artistPage.getId();
        this.socialNetwork = socialNetwork;
        this.subscribersAmount = subscribersAmount;
        this.liveSubscribers = liveSubscribers;
    }

    @Override
    public String toString(){
        return "\nSocialMediaStatistic{" + "id=" + this.id + ", artist_or_group_id=" +
                this.artistOrGroup + ", social_network=" + this.socialNetwork +
                ", subscribers_amount=" + this.subscribersAmount +
                ", live_subscribers=" + this.liveSubscribers +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        SocialMediaStatistic socialMediaStatistic = (SocialMediaStatistic) o;
        return id != null && Objects.equals(id, socialMediaStatistic.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
