package ru.nsu.ashikhmin.music_studio_app.postdatasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDataSource {

    @JsonProperty("client_id")
    private Long clientId;
    @JsonProperty("group_id")
    private Long groupId;
    @JsonProperty("stage_name")
    private String stageName;
    private String genre;
    @JsonProperty("creation_date")
    private Date creationDate;

    public ArtistDataSource(Long clientId, String stageName, String genre, Date creationDate){
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
