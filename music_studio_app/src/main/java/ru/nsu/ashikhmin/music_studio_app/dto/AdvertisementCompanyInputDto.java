package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

import java.sql.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementCompanyInputDto {
    private Long artistPageId;
    private String advertisementCompanyName;
    private Date startDate;
    private Date endDate;
    private String auditoryType;
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
