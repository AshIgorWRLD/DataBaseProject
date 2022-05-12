package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDataSource {

    @JsonProperty("user_id")
    private Long userId;
    private String post;

    @Override
    public String toString(){
        return "EmployeeDataSource{" + "user_id=" + this.userId +
                ", post=" + this.post + "}";
    }
}
