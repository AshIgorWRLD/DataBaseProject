package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ConcertAndContractIncomeDataSource {
    @JsonProperty("artist_or_group_id")
    private Long artistOrGroupId;
    private String type;
    private String name;
    @JsonProperty("money_amount")
    private Long moneyAmount;

    @Override
    public String toString(){
        return "ConcertAndContractIncomeDataSource{" + "artist_or_group_id=" +
                this.artistOrGroupId + ", type=" + this.type + ", name=" + this.name +
                ", money_amount=" + this.moneyAmount + "}";
    }
}
