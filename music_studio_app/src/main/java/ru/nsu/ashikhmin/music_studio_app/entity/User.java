package ru.nsu.ashikhmin.music_studio_app.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotBlank
    @NotNull
    @Column(unique = true)
    @Size(min = 6, max = 30)
    private String login;

    @NotBlank
    @NotNull
    @Size(min = 6, max = 30)
    private String password;

    public User(){}

    public User(String name, String login, String password){
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString(){
        return "\nUser{" + "id=" + this.id + ", name=" + this.name + ", login=" +
                this.login + ", password=" + this.password + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){
            return false;
        }
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
