package ru.nsu.ashikhmin.music_studio_app.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "investors")
@Getter
@Setter
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @Column(name = "invested_money")
    @Min(value = 100)
    @Max(value = 99999999)
    private Long investedMoney;

    @NotNull
    @Column(name = "business_part")
    @Min(value = 0)
    @Max(value = 1)
    private Double businessPart;

    public Investor(){}

    public Investor(User user, Long investedMoney, Double businessPart){
        this.user = user;
        this.investedMoney = investedMoney;
        this.businessPart = businessPart;
    }

    @Override
    public String toString(){
        return "Investor{" + "id=" + this.id + ", user=" + this.user +
                ", invested_money=" + this.investedMoney + ", business_part="
                + this.businessPart + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        Investor investor = (Investor) o;
        return id != null && Objects.equals(id, investor.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
