package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestorInputDto {

    private Long userId;
    private Long investedMoney;
    private Double businessPart;

    @Override
    public String toString(){
        return "InvestorDataSource{" + "user_id=" + this.userId +
                ", invested_money=" + this.investedMoney + ", business_part="
                + this.businessPart + "}";
    }
}
