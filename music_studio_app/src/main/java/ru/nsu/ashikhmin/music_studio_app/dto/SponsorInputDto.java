package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SponsorInputDto {
    private Long eventId;
    private String name;
    private String businessType;
    private Long sponsoredMoney;

    @Override
    public String toString(){
        return "\nSponsor{" + "event_id=" + this.eventId +
                ", name=" + this.name + ", business_type=" + this.businessType +
                ", sponsored_money=" + this.sponsoredMoney + "}";
    }
}
