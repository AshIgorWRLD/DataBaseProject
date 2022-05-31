package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

import java.sql.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgressInputDto {
    private Long artistPageId;
    private Double socialMediaCoefficient;
    private Double advertisementCompaniesCoefficient;
    private Double distributionCoefficient;
    private Double incomesCoefficient;
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
