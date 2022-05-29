package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInputDto {

    private Long userId;
    private String post;

    @Override
    public String toString(){
        return "EmployeeDataSource{" + "user_id=" + this.userId +
                ", post=" + this.post + "}";
    }
}
