package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "sponsors")
@Getter
@Setter
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @NotNull
    @NotBlank
    @Size(min = 3)
    private String name;

    @NotNull
    @NotBlank
    @JsonProperty("business_type")
    @Column(name = "business_type")
    private String businessType;

    @NotNull
    @NotBlank
    @JsonProperty("sponsored_money")
    @Column(name = "sponsored_money")
    @Min(value = 5000)
    private Long sponsoredMoney;

    public Sponsor(){}

    public Sponsor(Event event, String name, String businessType, Long sponsoredMoney) {
        this.event = event;
        this.name = name;
        this.businessType = businessType;
        this.sponsoredMoney = sponsoredMoney;
    }

    @Override
    public String toString(){
        return "\nSponsor{" + "id=" + this.id + ", event=" + this.event +
                ", name=" + this.name + ", business_type=" + this.businessType +
                ", sponsored_money=" + this.sponsoredMoney + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        Sponsor sponsor = (Sponsor) o;
        return id != null && Objects.equals(id, sponsor.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
