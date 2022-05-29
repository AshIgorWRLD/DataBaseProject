package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestorPaymentInputDto {

    private Long investorId;
    private Long investorBillId;
    private Long amount;

    @Override
    public String toString(){
        return "InvestorPaymentDataSource{" + "investor_id=" + this.investorId +
                ", investor_bill_id=" + this.investorBillId + ", amount=" + this.amount + "}";
    }
}
