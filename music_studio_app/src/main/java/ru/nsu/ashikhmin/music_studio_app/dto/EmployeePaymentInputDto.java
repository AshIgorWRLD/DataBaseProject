package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePaymentInputDto {

    private Long employeeId;
    private Long employeeBillId;
    private Long amount;

    @Override
    public String toString(){
        return "EmployeePaymentDataSource{" + "employee_id=" + this.employeeId +
                ", employee_bill_id=" + this.employeeBillId + ", amount=" + this.amount + "}";
    }
}
