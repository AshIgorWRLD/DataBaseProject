package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestorPaymentDataSource {

    @JsonProperty("investor_id")
    private Long investorId;
    @JsonProperty("investor_bill_id")
    private Long investorBillId;
    private Long amount;

    @Override
    public String toString(){
        return "InvestorPaymentDataSource{" + "investor_id=" + this.investorId +
                ", investor_bill_id=" + this.investorBillId + ", amount=" + this.amount + "}";
    }
}
