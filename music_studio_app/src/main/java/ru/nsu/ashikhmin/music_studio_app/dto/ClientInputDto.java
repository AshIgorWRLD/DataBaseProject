package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientInputDto {

    private Long userId;
    private String type;

    @Override
    public String toString(){
        return "ClientDataSource{" + "user_id=" + this.userId +
                ", type=" + this.type + "}";
    }
}
