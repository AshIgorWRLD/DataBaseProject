package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistPaymentInputDto {

    private Long artistId;
    private Long artistBillId;
    private Long amount;

    @Override
    public String toString(){
        return "ArtistPaymentDataSource{" + "artist_id=" + this.artistId +
                ", artist_bill_id=" + this.artistBillId + ", amount=" + this.amount + "}";
    }
}
