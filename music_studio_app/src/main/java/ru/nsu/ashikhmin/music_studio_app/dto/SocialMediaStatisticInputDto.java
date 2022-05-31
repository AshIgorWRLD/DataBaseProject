package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocialMediaStatisticInputDto {
    private Long artistPageId;
    private String socialNetwork;
    private Long subscribersAmount;
    private Long liveSubscribers;

    @Override
    public String toString(){
        return "\nSocialMediaStatistic{" + "artist_or_group_id=" +
                this.artistPageId + ", social_network=" + this.socialNetwork +
                ", subscribers_amount=" + this.subscribersAmount +
                ", live_subscribers=" + this.liveSubscribers +"}";
    }
}
