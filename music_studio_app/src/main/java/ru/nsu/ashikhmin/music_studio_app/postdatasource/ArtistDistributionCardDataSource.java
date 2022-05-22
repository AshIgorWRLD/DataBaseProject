package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDistributionCardDataSource {
    @JsonProperty("artist_or_group_id")
    private Long artistPageId;
    @JsonProperty("distribution_service_id")
    private Long distributionServiceId;
    @JsonProperty("listen_watch_amount")
    private Long listenWatchAmount;
    @JsonProperty("monthly_listeners")
    private Long monthlyListeners;

    @Override
    public String toString(){
        return "\nArtistDistributionCard{" + "artist_or_group_id=" + this.artistPageId +
                ", distribution_service_id=" + this.distributionServiceId +
                ", listen_watch_amount=" + this.listenWatchAmount +
                ", monthly_listeners=" + this.monthlyListeners +"}";
    }
}
