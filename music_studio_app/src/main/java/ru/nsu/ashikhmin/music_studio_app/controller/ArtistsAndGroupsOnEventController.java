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
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistPage;
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistsAndGroupsOnEvent;
import ru.nsu.ashikhmin.music_studio_app.entity.Event;
import ru.nsu.ashikhmin.music_studio_app.entity.Progress;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.postdatasource.ArtistsAndGroupsOnEventDataSource;
import ru.nsu.ashikhmin.music_studio_app.repository.ArtistsAndGroupsOnEventRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("events/participants")
@Api(description = "Контроллер участников мероприятий")
public class ArtistsAndGroupsOnEventController {

    private final ArtistsAndGroupsOnEventRepo artistsAndGroupsOnEventRepo;

    private final ArtistPageController artistPageController;
    private final EventController eventController;

    @Autowired
    public ArtistsAndGroupsOnEventController(ArtistsAndGroupsOnEventRepo artistsAndGroupsOnEventRepo,
                                             ArtistPageController artistPageController,
                                             EventController eventController){
        this.artistsAndGroupsOnEventRepo = artistsAndGroupsOnEventRepo;
        this.artistPageController = artistPageController;
        this.eventController = eventController;
    }

    @GetMapping
    @ApiOperation("Получение списка страниц исполнителей")
    public ResponseEntity<List<ArtistsAndGroupsOnEvent>> list(){
        log.info("request for getting all artistsAndGroupsOnEvents");
        List<ArtistsAndGroupsOnEvent> artistsAndGroupsOnEvents = artistsAndGroupsOnEventRepo.findAll();
        if(artistsAndGroupsOnEvents.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(artistsAndGroupsOnEvents, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение страницы исполнителя по id")
    public ResponseEntity<ArtistsAndGroupsOnEvent> getOne(@PathVariable("id") Long id) {
        log.info("request for getting artistsAndGroupsOnEvent with id: {}", id);

        ArtistsAndGroupsOnEvent artistsAndGroupsOnEvent = artistsAndGroupsOnEventRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found artistsAndGroupsOnEvent with id = " + id));

        return new ResponseEntity<>(artistsAndGroupsOnEvent, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание новой страницы исполнителя")
    public ResponseEntity<ArtistsAndGroupsOnEvent> create(@Valid @RequestBody ArtistsAndGroupsOnEventDataSource artistsAndGroupsOnEventDataSource){
        log.info("request for creating artistsAndGroupsOnEvent from data source {}",
                artistsAndGroupsOnEventDataSource);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                artistsAndGroupsOnEventDataSource.getArtistPageId());
        ResponseEntity<Event> eventResponseEntity = eventController.getOne(
                artistsAndGroupsOnEventDataSource.getEventId());
        ArtistsAndGroupsOnEvent artistsAndGroupsOnEvent = new ArtistsAndGroupsOnEvent(
                artistPageResponseEntity.getBody(),
                eventResponseEntity.getBody(),
                artistsAndGroupsOnEventDataSource.getPerformanceTime(),
                artistsAndGroupsOnEventDataSource.getIncome());

        log.info("request for creating artistsAndGroupsOnEvent with parameters {}", artistsAndGroupsOnEvent);

        return new ResponseEntity<>(artistsAndGroupsOnEventRepo.save(artistsAndGroupsOnEvent), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующей странице исполнителя")
    public ResponseEntity<ArtistsAndGroupsOnEvent> update(@PathVariable("id") long id,
                                       @Valid @RequestBody ArtistsAndGroupsOnEventDataSource artistsAndGroupsOnEventDataSource){

        log.info("request for updating artistsAndGroupsOnEvent by id {} with parameters {}",
                id, artistsAndGroupsOnEventDataSource);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                artistsAndGroupsOnEventDataSource.getArtistPageId());
        ResponseEntity<Event> eventResponseEntity = eventController.getOne(
                artistsAndGroupsOnEventDataSource.getEventId());
        ArtistsAndGroupsOnEvent artistsAndGroupsOnEvent = new ArtistsAndGroupsOnEvent(
                artistPageResponseEntity.getBody(),
                eventResponseEntity.getBody(),
                artistsAndGroupsOnEventDataSource.getPerformanceTime(),
                artistsAndGroupsOnEventDataSource.getIncome());
        ArtistsAndGroupsOnEvent artistsAndGroupsOnEventFromDataBase = artistsAndGroupsOnEventRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found artistsAndGroupsOnEvent with id = " + id));
        log.info("ArtistsAndGroupsOnEvent from data base: " + artistsAndGroupsOnEventFromDataBase);
        BeanUtils.copyProperties(artistsAndGroupsOnEvent, artistsAndGroupsOnEventFromDataBase,
                NullProperty.getNullPropertiesString(artistsAndGroupsOnEvent));
        return new ResponseEntity<>(artistsAndGroupsOnEventRepo.save(artistsAndGroupsOnEventFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление страницы исполнителя")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") ArtistsAndGroupsOnEvent artistsAndGroupsOnEvent) {

        log.info("request for deleting artistsAndGroupsOnEvent with parameters {}", artistsAndGroupsOnEvent);

        artistsAndGroupsOnEventRepo.delete(artistsAndGroupsOnEvent);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех страниц исполнителей")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all artistsAndGroupsOnEvents");
        artistsAndGroupsOnEventRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
