package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee_payments")
@Getter
@Setter
public class EmployeePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @JsonProperty("employee_bill_id")
    @Column(name = "employee_bill_id")
    private Long employeeBillId;

    @NotNull
    @JsonManagedReference
    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(name = "bills_of_employees",
            joinColumns = {@JoinColumn(name = "employee_payment_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_bill_id")})
    private Set<EmployeeBill> employeeBills;

    @NotNull
    @Positive
    private Long amount;

    public EmployeePayment(){}

    public EmployeePayment(Employee employee, Set<EmployeeBill> employeeBills, Long amount){
        this.employee = employee;
        this.employeeBills = employeeBills;
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "Employee{" + "id=" + this.id + ", employee=" + this.employee +
                ", employee_bill=" + this.employeeBills + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        EmployeePayment employeePayments = (EmployeePayment) o;
        return id != null && Objects.equals(id, employeePayments.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
