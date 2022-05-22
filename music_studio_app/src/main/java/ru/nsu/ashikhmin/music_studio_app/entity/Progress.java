package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "progress")
@Getter
@Setter
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    private Long artistPageId;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "artist_or_group_id", referencedColumnName = "id")
    private ArtistPage artistPage;

    @NotNull
    @JsonProperty("social_media_coefficient")
    @Column(name = "social_media_coefficient")
    private Double socialMediaCoefficient;

    @NotNull
    @JsonProperty("advertisement_companies_coefficient")
    @Column(name = "advertisement_companies_coefficient")
    private Double advertisementCompaniesCoefficient;

    @NotNull
    @JsonProperty("distribution_coefficient")
    @Column(name = "distribution_coefficient")
    private Double distributionCoefficient;

    @NotNull
    @JsonProperty("incomes_coefficient")
    @Column(name = "incomes_coefficient")
    private Double incomesCoefficient;

    @NotNull
    @JsonProperty("supposed_success_date")
    @Column(name = "supposed_success_date")
    private Date supposedSuccessDate;

    public Progress(){}

    public Progress(ArtistPage artistPage, Double socialMediaCoefficient,
                    Double advertisementCompaniesCoefficient, Double distributionCoefficient,
                    Double incomesCoefficient, Date supposedSuccessDate) {
        this.artistPage = artistPage;
        this.artistPageId = artistPage.getId();
        this.socialMediaCoefficient = socialMediaCoefficient;
        this.advertisementCompaniesCoefficient = advertisementCompaniesCoefficient;
        this.distributionCoefficient = distributionCoefficient;
        this.incomesCoefficient = incomesCoefficient;
        this.supposedSuccessDate = supposedSuccessDate;
    }

    @Override
    public String toString(){
        return "\nProgress{" + "id=" + this.id + ", artist_or_group=" + this.artistPage +
                ", social_media_coefficient=" + this.socialMediaCoefficient +
                ", advertisement_companies_coefficient=" + this.advertisementCompaniesCoefficient +
                ", distribution_coefficient=" + this.distributionCoefficient +
                ", incomes_coefficient=" + this.incomesCoefficient +
                ", supposed_success_date=" + this.supposedSuccessDate + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        Progress progress = (Progress) o;
        return id != null && Objects.equals(id, progress.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
