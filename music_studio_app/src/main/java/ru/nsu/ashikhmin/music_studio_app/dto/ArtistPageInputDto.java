package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistPageInputDto {
    private Long artistId;
    private Long groupId;

    @Override
    public String toString(){
        return "ArtistPageDataSource{" + "solo_artist_id=" + this.artistId +
                ", group_id=" + this.groupId + "}";
    }
}
