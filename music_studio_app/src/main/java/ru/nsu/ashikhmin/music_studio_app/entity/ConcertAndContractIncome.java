package ru.nsu.ashikhmin.music_studio_app.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "concert_and_contract_incomes")
@Getter
@Setter
public class ConcertAndContractIncome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "artist_or_group_id", referencedColumnName = "id")
    private ArtistPage artistPage;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    @Size(min = 9)
    private String name;

    @NotNull
    @Positive
    private Long moneyAmount;

    public ConcertAndContractIncome(){}

    public ConcertAndContractIncome(ArtistPage artistPage, String type,
                                    String name, Long moneyAmount) {
        this.artistPage = artistPage;
        this.type = type;
        this.name = name;
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString(){
        return "ConcertAndContractIncome{" + "id=" + this.id + ", artist_or_group=" +
                this.artistPage + ", type=" + this.type + ", name=" + this.name +
                ", money_amount=" + this.moneyAmount + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        ConcertAndContractIncome concertAndContractIncome = (ConcertAndContractIncome) o;
        return id != null && Objects.equals(id, concertAndContractIncome.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
