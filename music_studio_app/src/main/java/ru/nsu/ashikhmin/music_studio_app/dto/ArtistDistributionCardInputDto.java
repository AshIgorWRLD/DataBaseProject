package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDistributionCardInputDto {
    private Long artistPageId;
    private Long distributionServiceId;
    private Long listenWatchAmount;
    private Long monthlyListeners;

    @Override
    public String toString(){
        return "\nArtistDistributionCard{" + "artist_or_group_id=" + this.artistPageId +
                ", distribution_service_id=" + this.distributionServiceId +
                ", listen_watch_amount=" + this.listenWatchAmount +
                ", monthly_listeners=" + this.monthlyListeners +"}";
    }
}
