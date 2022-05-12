package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.boot.context.properties.bind.Name;
import ru.nsu.ashikhmin.music_studio_app.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestorDataSource {

    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("invested_money")
    private Long investedMoney;
    @JsonProperty("business_part")
    private Double businessPart;

    @Override
    public String toString(){
        return "InvestorDataSource{" + "user_id=" + this.userId +
                ", invested_money=" + this.investedMoney + ", business_part="
                + this.businessPart + "}";
    }
}
