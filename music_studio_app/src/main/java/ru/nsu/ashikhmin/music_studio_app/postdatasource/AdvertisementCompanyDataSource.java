package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistPage;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementCompanyDataSource {
    @JsonProperty("artist_or_group_id")
    private Long artistPageId;
    @JsonProperty("advertisement_company_name")
    private String advertisementCompanyName;
    @NotNull
    @JsonProperty("start_date")
    private Date startDate;
    @JsonProperty("end_date")
    private Date endDate;
    @JsonProperty("auditory_type")
    private String auditoryType;
    @JsonProperty("advertisement_type")
    private String advertisementType;
    private Long expenses;

    @Override
    public String toString(){
        return "AdvertisementCompany{" + "artist_or_group=" + this.artistPageId +
                ", advertisement_company_name=" + this.advertisementCompanyName +
                ", start_date=" + this.startDate + ", end_date=" + this.endDate +
                ", auditory_type=" + this.auditoryType + ", advertisement_type=" +
                this.advertisementType + ", expenses=" + this.expenses + "}";
    }

}
