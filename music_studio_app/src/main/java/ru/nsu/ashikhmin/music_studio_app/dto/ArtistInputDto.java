package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.*;

import java.sql.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistInputDto {

    private Long clientId;
    private Long groupId;
    private String stageName;
    private String genre;
    private Date creationDate;

    public ArtistInputDto(Long clientId, String stageName, String genre, Date creationDate){
        this.clientId = clientId;
        this.stageName = stageName;
        this.genre = genre;
        this.creationDate = creationDate;
    }

    @Override
    public String toString(){
        return "ArtistDataSource{" + "client_id=" + this.clientId +
                ", group_id=" + this.groupId + ", stage_name=" + this.stageName
                + ", genre=" + this.genre + ", creation_date=" + this.creationDate + "}";
    }

}
