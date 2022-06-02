package ru.nsu.ashikhmin.music_studio_app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ashikhmin.music_studio_app.entity.Artist;
import ru.nsu.ashikhmin.music_studio_app.entity.Client;
import ru.nsu.ashikhmin.music_studio_app.entity.Group;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.dto.ArtistInputDto;
import ru.nsu.ashikhmin.music_studio_app.repository.ArtistRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("users/clients/artists")
@Api(description = "Контроллер исполнителей")
public class ArtistController {
    private final ArtistRepo artistRepo;

    private final ClientController clientController;
    private final GroupController groupController;

    @Autowired
    public ArtistController(ArtistRepo artistRepo, ClientController clientController,
                            GroupController groupController){
        this.artistRepo = artistRepo;
        this.clientController = clientController;
        this.groupController = groupController;
    }

    @GetMapping
    @ApiOperation("Получение списка исполнителей")
    public ResponseEntity<Page<Artist>> list(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        log.info("request for getting all artists");
        Page<Artist> artists = artistRepo.findAll(pageable);
        if(artists.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение исполнителя по id")
    public ResponseEntity<Artist> getOne(@PathVariable("id") Long id) {
        log.info("request for getting artist with id: {}", id);

        Artist artist = artistRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found artist with id = " + id));

        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового исполнителя")
    public ResponseEntity<Artist> create(@Valid @RequestBody ArtistInputDto artistInputDto){
        log.info("request for creating artist from data source {}", artistInputDto);
        ResponseEntity<Client> clientResponseEntity = clientController.getOne(
                artistInputDto.getClientId());
        ResponseEntity<Group> groupResponseEntity = groupController.getOne(
                artistInputDto.getGroupId());
        Group group = groupResponseEntity.getBody();
        Artist artist = new Artist(clientResponseEntity.getBody(),
                group, artistInputDto.getStageName(),
                artistInputDto.getGenre(), artistInputDto.getCreationDate());
        artist.setGroupId(group.getId());
        log.info("request for creating artist with parameters {}", artist);
        return new ResponseEntity<>(artistRepo.save(artist), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем исполнителе")
    public ResponseEntity<Artist> update(@PathVariable("id") long id,
                                         @Valid @RequestBody ArtistInputDto artistInputDto){

        log.info("request for updating artist by id {} with parameters {}",
                id, artistInputDto);
        ResponseEntity<Client> clientResponseEntity = clientController.getOne(
                artistInputDto.getClientId());
        ResponseEntity<Group> groupResponseEntity = groupController.getOne(
                artistInputDto.getGroupId());
        Group group = groupResponseEntity.getBody();
        Artist artist = new Artist(clientResponseEntity.getBody(),
                group, artistInputDto.getStageName(),
                artistInputDto.getGenre(), artistInputDto.getCreationDate());
        artist.setGroupId(group.getId());
        Artist artistFromDataBase = artistRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found artist with id = " + id));
        log.info("Artist from data base: " + artistFromDataBase);

        BeanUtils.copyProperties(artist, artistFromDataBase,
                NullProperty.getNullPropertiesString(artist));
        return new ResponseEntity<>(artistRepo.save(artistFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление исполнителя")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Artist artist) {

        log.info("request for deleting artist with parameters {}", artist);

        artistRepo.delete(artist);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех исполнителей")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all artists");
        artistRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
