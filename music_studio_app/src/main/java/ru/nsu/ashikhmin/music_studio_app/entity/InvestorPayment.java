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
@Table(name = "investor_payments")
@Getter
@Setter
public class InvestorPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "investor_id", referencedColumnName = "id")
    private Investor investor;

    @Column(name = "investor_bill_id")
    private Long investorBillId;

    @NotNull
    @JsonManagedReference
    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(name = "bills_of_investors",
            joinColumns = {@JoinColumn(name = "investor_payment_id")},
            inverseJoinColumns = {@JoinColumn(name = "investor_bill_id")})
    private Set<InvestorBill> investorBills;

    @NotNull
    @Positive
    private Long amount;

    public InvestorPayment(){}

    public InvestorPayment(Investor investor, Set<InvestorBill> investorBills, Long amount){
        this.investor = investor;
        this.investorBills = investorBills;
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "Investor{" + "id=" + this.id + ", investor=" + this.investor +
                ", investor_bill=" + this.investorBills + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        InvestorPayment investorPayments = (InvestorPayment) o;
        return id != null && Objects.equals(id, investorPayments.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
