package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistPaymentDataSource {

    @JsonProperty("artist_id")
    private Long artistId;
    @JsonProperty("artist_bill_id")
    private Long artistBillId;
    private Long amount;

    @Override
    public String toString(){
        return "ArtistPaymentDataSource{" + "artist_id=" + this.artistId +
                ", artist_bill_id=" + this.artistBillId + ", amount=" + this.amount + "}";
    }
}
