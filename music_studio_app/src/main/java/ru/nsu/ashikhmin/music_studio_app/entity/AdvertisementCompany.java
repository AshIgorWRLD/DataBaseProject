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
@Table(name = "advertisement_companies")
@Getter
@Setter
public class AdvertisementCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "artist_or_group_id", referencedColumnName = "id")
    private ArtistPage artistPage;

    @NotNull
    @NotBlank
    @JsonProperty("advertisement_company_name")
    @Column(name = "advertisement_company_name")
    private String advertisementCompanyName;

    @NotNull
    @JsonProperty("start_date")
    @Column(name = "start_date")
    private Date startDate;

    @NotNull
    @JsonProperty("end_date")
    @Column(name = "end_date")
    private Date endDate;

    @NotNull
    @NotBlank
    @JsonProperty("auditory_type")
    @Column(name = "auditory_type")
    private String auditoryType;

    @NotNull
    @NotBlank
    @JsonProperty("advertisement_type")
    @Column(name = "advertisement_type")
    private String advertisementType;

    @NotNull
    private Long expenses;

    public AdvertisementCompany(){}

    public AdvertisementCompany(ArtistPage artistPage, String advertisementCompanyName,
                                Date startDate, Date endDate, String auditoryType,
                                String advertisementType, Long expenses) {
        this.artistPage = artistPage;
        this.advertisementCompanyName = advertisementCompanyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.auditoryType = auditoryType;
        this.advertisementType = advertisementType;
        this.expenses = expenses;
    }

    @Override
    public String toString(){
        return "AdvertisementCompany{" + "id=" + this.id + ", artist_or_group=" + this.artistPage +
                ", advertisement_company_name=" + this.advertisementCompanyName +
                ", start_date=" + this.startDate + ", end_date=" + this.endDate +
                ", auditory_type=" + this.auditoryType + ", advertisement_type=" +
                this.advertisementType + ", expenses=" + this.expenses + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        AdvertisementCompany advertisementCompany = (AdvertisementCompany) o;
        return id != null && Objects.equals(id, advertisementCompany.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
