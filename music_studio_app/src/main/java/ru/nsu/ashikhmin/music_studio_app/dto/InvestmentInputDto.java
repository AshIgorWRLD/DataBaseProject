package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentInputDto {

    private Long investorId;
    private Long recipientId;
    private Long moneyAmount;

    @Override
    public String toString(){
        return "InvestmentDataSource{" + "investor_id=" + this.investorId +
                ", recipient_id=" + this.recipientId + ", money_amount=" + this.moneyAmount + "}";
    }
}
