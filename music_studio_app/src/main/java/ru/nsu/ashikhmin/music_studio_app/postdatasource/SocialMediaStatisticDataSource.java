package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocialMediaStatisticDataSource {
    @JsonProperty("artist_or_group_id")
    private Long artistPageId;
    @JsonProperty("social_network")
    private String socialNetwork;
    @JsonProperty("subscribers_amount")
    private Long subscribersAmount;
    @JsonProperty("live_subscribers")
    private Long liveSubscribers;

    @Override
    public String toString(){
        return "\nSocialMediaStatistic{" + "artist_or_group_id=" +
                this.artistPageId + ", social_network=" + this.socialNetwork +
                ", subscribers_amount=" + this.subscribersAmount +
                ", live_subscribers=" + this.liveSubscribers +"}";
    }
}
