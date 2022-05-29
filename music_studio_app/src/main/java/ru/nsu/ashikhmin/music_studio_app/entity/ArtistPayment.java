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
@Table(name = "artist_payments")
@Getter
@Setter
public class ArtistPayment {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private Artist artist;

    @Column(name = "artist_bill_id")
    private Long artistBillId;

    @NotNull
    @JsonManagedReference
    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(name = "bills_of_artists",
            joinColumns = {@JoinColumn(name = "artist_payment_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_bill_id")})
    private Set<ArtistBill> artistBills;

    @NotNull
    @Positive
    private Long amount;

    public ArtistPayment(){}

    public ArtistPayment(Artist artist, Set<ArtistBill> artistBills, Long amount){
        this.artist = artist;
        this.artistBills = artistBills;
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "Artist{" + "id=" + this.id + ", artist=" + this.artist +
                ", artist_bill=" + this.artistBills + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        ArtistPayment artistPayments = (ArtistPayment) o;
        return id != null && Objects.equals(id, artistPayments.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
