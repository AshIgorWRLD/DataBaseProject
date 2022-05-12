package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDataSource {

    @JsonProperty("user_id")
    private Long userId;
    private String type;

    @Override
    public String toString(){
        return "ClientDataSource{" + "user_id=" + this.userId +
                ", type=" + this.type + "}";
    }
}
