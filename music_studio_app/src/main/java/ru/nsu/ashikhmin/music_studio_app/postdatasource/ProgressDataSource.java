package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgressDataSource {
    @JsonProperty("artist_or_group_id")
    private Long artistPageId;
    @JsonProperty("social_media_coefficient")
    private Double socialMediaCoefficient;
    @JsonProperty("advertisement_companies_coefficient")
    private Double advertisementCompaniesCoefficient;
    @JsonProperty("distribution_coefficient")
    private Double distributionCoefficient;
    @JsonProperty("incomes_coefficient")
    private Double incomesCoefficient;
    @JsonProperty("supposed_success_date")
    private Date supposedSuccessDate;

    @Override
    public String toString(){
        return "\nProgress{" + ", artist_or_group_id=" + this.artistPageId +
                ", social_media_coefficient=" + this.socialMediaCoefficient +
                ", advertisement_companies_coefficient=" + this.advertisementCompaniesCoefficient +
                ", distribution_coefficient=" + this.distributionCoefficient +
                ", incomes_coefficient=" + this.incomesCoefficient +
                ", supposed_success_date=" + this.supposedSuccessDate + "}";
    }
}
