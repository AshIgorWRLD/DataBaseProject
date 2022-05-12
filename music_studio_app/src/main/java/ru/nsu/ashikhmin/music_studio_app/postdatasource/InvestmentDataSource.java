package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentDataSource {

    @JsonProperty("investor_id")
    private Long investorId;
    @JsonProperty("recipient_id")
    private Long recipientId;
    @JsonProperty("money_amount")
    private Long moneyAmount;

    @Override
    public String toString(){
        return "InvestmentDataSource{" + "investor_id=" + this.investorId +
                ", recipient_id=" + this.recipientId + ", money_amount=" + this.moneyAmount + "}";
    }
}
