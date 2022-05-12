package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee_bills")
@Getter
@Setter
public class EmployeeBill {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonProperty("pay_day")
    @Column(name = "pay_day")
    private Date payDay;

    @NotNull
    @JsonProperty("month_to_work")
    @Column(name = "month_to_work")
    private Integer monthToWork;

    @JsonBackReference
    @ManyToMany(mappedBy = "employeeBills")
    private Set<EmployeePayment> employeePayments = new HashSet<>();

    public EmployeeBill(){}

    public EmployeeBill(Date payDay, Integer monthToWork){
        this.payDay = payDay;
        this.monthToWork = monthToWork;
    }

    @Override
    public String toString(){
        return "EmployeeBill{" + "id=" + this.id + ", pay_day=" + this.payDay +
                ", month_to_work=" + this.monthToWork + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        EmployeeBill employeeBill = (EmployeeBill) o;
        return id != null && Objects.equals(id, employeeBill.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
