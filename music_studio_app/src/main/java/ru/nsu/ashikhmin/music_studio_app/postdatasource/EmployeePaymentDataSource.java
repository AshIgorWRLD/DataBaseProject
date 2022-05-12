package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePaymentDataSource {

    @JsonProperty("employee_id")
    private Long employeeId;
    @JsonProperty("employee_bill_id")
    private Long employeeBillId;
    private Long amount;

    @Override
    public String toString(){
        return "EmployeePaymentDataSource{" + "employee_id=" + this.employeeId +
                ", employee_bill_id=" + this.employeeBillId + ", amount=" + this.amount + "}";
    }
}
