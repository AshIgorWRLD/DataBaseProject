package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SponsorDataSource {
    @JsonProperty("event_id")
    private Long eventId;
    private String name;
    @JsonProperty("business_type")
    private String businessType;
    @JsonProperty("sponsored_money")
    private Long sponsoredMoney;

    @Override
    public String toString(){
        return "\nSponsor{" + "event_id=" + this.eventId +
                ", name=" + this.name + ", business_type=" + this.businessType +
                ", sponsored_money=" + this.sponsoredMoney + "}";
    }
}
