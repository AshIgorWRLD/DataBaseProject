package ru.nsu.ashikhmin.music_studio_app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ashikhmin.music_studio_app.entity.Artist;
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistPage;
import ru.nsu.ashikhmin.music_studio_app.entity.Group;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.postdatasource.ArtistPageDataSource;
import ru.nsu.ashikhmin.music_studio_app.repository.ArtistPageRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("artist_pages")
@Api(description = "Контроллер страниц исполнителей")
public class ArtistPageController {
    
    private final ArtistPageRepo artistPageRepo;

    private final ArtistController artistController;
    private final GroupController groupController;

    @Autowired
    public ArtistPageController(ArtistPageRepo artistPageRepo,
                            ArtistController artistController,
                            GroupController groupController){
        this.artistPageRepo = artistPageRepo;
        this.artistController = artistController;
        this.groupController = groupController;
    }

    @GetMapping
    @ApiOperation("Получение списка страниц исполнителей")
    public ResponseEntity<List<ArtistPage>> list(){
        log.info("request for getting all artistPages");
        List<ArtistPage> artistPages = artistPageRepo.findAll();
        if(artistPages.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(artistPages, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение страницы исполнителя по id")
    public ResponseEntity<ArtistPage> getOne(@PathVariable("id") Long id) {
        log.info("request for getting artistPage with id: {}", id);

        ArtistPage artistPage = artistPageRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found artistPage with id = " + id));

        return new ResponseEntity<>(artistPage, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание новой страницы исполнителя")
    public ResponseEntity<ArtistPage> create(@Valid @RequestBody ArtistPageDataSource artistPageDataSource){
        log.info("request for creating artistPage from data source {}", artistPageDataSource);
        ArtistPage artistPage;
        if(artistPageDataSource.getGroupId() == null){
            ResponseEntity<Artist> artistResponseEntity = artistController.getOne(
                    artistPageDataSource.getArtistId());
            artistPage = new ArtistPage(artistResponseEntity.getBody());
        }else {
            ResponseEntity<Group> groupResponseEntity = groupController.getOne(
                    artistPageDataSource.getGroupId());
            artistPage = new ArtistPage(groupResponseEntity.getBody());
        }
        log.info("request for creating artistPage with parameters {}", artistPage);

        return new ResponseEntity<>(artistPageRepo.save(artistPage), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующей странице исполнителя")
    public ResponseEntity<ArtistPage> update(@PathVariable("id") long id,
                                         @Valid @RequestBody ArtistPageDataSource artistPageDataSource){

        log.info("request for updating artistPage by id {} with parameters {}",
                id, artistPageDataSource);
        ArtistPage artistPage;
        if(artistPageDataSource.getGroupId() == null){
            ResponseEntity<Artist> artistResponseEntity = artistController.getOne(
                    artistPageDataSource.getArtistId());
            artistPage = new ArtistPage(artistResponseEntity.getBody());
        }else {
            ResponseEntity<Group> groupResponseEntity = groupController.getOne(
                    artistPageDataSource.getGroupId());
            artistPage = new ArtistPage(groupResponseEntity.getBody());
        }

        ArtistPage artistPageFromDataBase = artistPageRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found artistPage with id = " + id));
        log.info("ArtistPage from data base: " + artistPageFromDataBase);

        BeanUtils.copyProperties(artistPage, artistPageFromDataBase,
                NullProperty.getNullPropertiesString(artistPage));
        return new ResponseEntity<>(artistPageRepo.save(artistPageFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление страницы исполнителя")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") ArtistPage artistPage) {

        log.info("request for deleting artistPage with parameters {}", artistPage);

        artistPageRepo.delete(artistPage);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех страниц исполнителей")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all artistPages");
        artistPageRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}