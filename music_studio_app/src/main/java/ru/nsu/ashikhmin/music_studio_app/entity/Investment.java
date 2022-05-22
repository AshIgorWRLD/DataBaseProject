package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "investments")
@Getter
@Setter
public class Investment {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "investor_id", referencedColumnName = "id")
    private Investor investor;

    @NotNull
    @JsonManagedReference
    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(name = "investments_info",
            joinColumns = {@JoinColumn(name = "investment_id")},
            inverseJoinColumns = {@JoinColumn(name = "recipient_id")})
    private Set<ArtistPage> recipients;

    @NotNull
    @Positive
    private Long moneyAmount;

    public Investment(){}

    public Investment(Investor investor, Set<ArtistPage> recipients, Long moneyAmount){
        this.investor = investor;
        this.recipients = recipients;
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString(){
        return "Investment{" + "id=" + this.id + ", investor=" + this.investor +
                ", recipients=" + this.recipients + ", money_amount=" + this.moneyAmount + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        Investment investment = (Investment) o;
        return id != null && Objects.equals(id, investment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
