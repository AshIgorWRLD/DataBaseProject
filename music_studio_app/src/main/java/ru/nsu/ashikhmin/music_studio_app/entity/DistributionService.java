package ru.nsu.ashikhmin.music_studio_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "distribution_services")
@Getter
@Setter
public class DistributionService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 1)
    private String name;

    @NotBlank
    @NotNull
    private String type;

    @NotBlank
    @NotNull
    @Positive
    private Double listenWatchCost;

    public DistributionService(){}

    public DistributionService(String name, String type, Double listenWatchCost) {
        this.name = name;
        this.type = type;
        this.listenWatchCost = listenWatchCost;
    }

    @Override
    public String toString(){
        return "\nUser{" + "id=" + this.id + ", name=" + this.name + ", type=" +
                this.type + ", listen_watch_cost=" + this.listenWatchCost + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        DistributionService distributionService = (DistributionService) o;
        return id != null && Objects.equals(id, distributionService.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
