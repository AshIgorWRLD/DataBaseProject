package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistPageDataSource {
    @JsonProperty("solo_artist_id")
    private Long artistId;
    @JsonProperty("group_id")
    private Long groupId;

    @Override
    public String toString(){
        return "ArtistPageDataSource{" + "solo_artist_id=" + this.artistId +
                ", group_id=" + this.groupId + "}";
    }
}
