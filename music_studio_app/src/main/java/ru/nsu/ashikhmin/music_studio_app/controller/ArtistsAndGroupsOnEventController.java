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
import ru.nsu.ashikhmin.music_studio_app.dto.CustomArtistsAndGroupsOnEventOutputDto;
import ru.nsu.ashikhmin.music_studio_app.dto.CustomVisitOutDto;
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistPage;
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistsAndGroupsOnEvent;
import ru.nsu.ashikhmin.music_studio_app.entity.Event;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.dto.ArtistsAndGroupsOnEventInputDto;
import ru.nsu.ashikhmin.music_studio_app.repository.ArtistsAndGroupsOnEventRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;
import ru.nsu.ashikhmin.music_studio_app.utils.SQLAdds;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.util.ArrayList;
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
    private final EntityManager entityManager;

    @Autowired
    public ArtistsAndGroupsOnEventController(ArtistsAndGroupsOnEventRepo artistsAndGroupsOnEventRepo,
                                             ArtistPageController artistPageController,
                                             EventController eventController,
                                             EntityManager entityManager){
        this.artistsAndGroupsOnEventRepo = artistsAndGroupsOnEventRepo;
        this.artistPageController = artistPageController;
        this.eventController = eventController;
        this.entityManager = entityManager;
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

    @GetMapping("custom")
    @ApiOperation("Получение соответствий артистов и групп с мероприятиями")
    public ResponseEntity<List<CustomArtistsAndGroupsOnEventOutputDto>> getCustom(){

        StringBuilder sqlRequest = new StringBuilder();

        List<String> artistFields = new ArrayList<>();
        artistFields.add(SQLAdds.ARTIST_PAGES_TABLE + ".id");
        artistFields.add(SQLAdds.ARTIST_TABLE + ".stage_name");
        artistFields.add(SQLAdds.EVENT_TABLE + ".event_name");
        artistFields.add(SQLAdds.ARTISTS_AND_GROUPS_ON_EVENT_TABLE + ".performance_time");
        artistFields.add(SQLAdds.ARTISTS_AND_GROUPS_ON_EVENT_TABLE + ".income");

        List<String> groupFields = new ArrayList<>();
        groupFields.add(SQLAdds.ARTIST_PAGES_TABLE + ".id");
        groupFields.add(SQLAdds.GROUP_TABLE + ".name");
        groupFields.add(SQLAdds.EVENT_TABLE + ".event_name");
        groupFields.add(SQLAdds.ARTISTS_AND_GROUPS_ON_EVENT_TABLE + ".performance_time");
        groupFields.add(SQLAdds.ARTISTS_AND_GROUPS_ON_EVENT_TABLE + ".income");

        List<String> fromTables = new ArrayList<>();
        fromTables.add(SQLAdds.ARTISTS_AND_GROUPS_ON_EVENT_TABLE);

        SQLAdds.addSelectLine(sqlRequest, artistFields);
        SQLAdds.addFromLine(sqlRequest, fromTables);
        SQLAdds.addJoin(sqlRequest, SQLAdds.RIGHT_OUTER_JOIN, SQLAdds.ARTISTS_AND_GROUPS_ON_EVENT_TABLE,
                SQLAdds.EVENT_TABLE, "event_id", "id");
        SQLAdds.addJoin(sqlRequest, SQLAdds.INNER_JOIN, SQLAdds.ARTISTS_AND_GROUPS_ON_EVENT_TABLE,
                SQLAdds.ARTIST_PAGES_TABLE, "artist_or_group_id", "id");
        SQLAdds.addJoin(sqlRequest, SQLAdds.RIGHT_OUTER_JOIN, SQLAdds.ARTIST_PAGES_TABLE,
                SQLAdds.ARTIST_TABLE, "solo_artist_id", "id");
        sqlRequest.append(" ")
                .append(SQLAdds.UNION)
                .append(" ");
        SQLAdds.addSelectLine(sqlRequest, groupFields);
        SQLAdds.addFromLine(sqlRequest, fromTables);
        SQLAdds.addJoin(sqlRequest, SQLAdds.RIGHT_OUTER_JOIN, SQLAdds.ARTISTS_AND_GROUPS_ON_EVENT_TABLE,
                SQLAdds.EVENT_TABLE, "event_id", "id");
        SQLAdds.addJoin(sqlRequest, SQLAdds.INNER_JOIN, SQLAdds.ARTISTS_AND_GROUPS_ON_EVENT_TABLE,
                SQLAdds.ARTIST_PAGES_TABLE, "artist_or_group_id", "id");
        SQLAdds.addJoin(sqlRequest, SQLAdds.RIGHT_OUTER_JOIN, SQLAdds.ARTIST_PAGES_TABLE,
                SQLAdds.GROUP_TABLE, "group_id", "id");
        sqlRequest.append(" ")
                .append(SQLAdds.ORDER_BY)
                .append(" id;");

        log.info("Outer join request: " + sqlRequest.toString());
        List<CustomArtistsAndGroupsOnEventOutputDto> out = entityManager.createNativeQuery(sqlRequest.toString(),
                CustomArtistsAndGroupsOnEventOutputDto.class).getResultList();
        return new ResponseEntity<>(out, HttpStatus.OK);
    }


    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание новой страницы исполнителя")
    public ResponseEntity<ArtistsAndGroupsOnEvent> create(@Valid @RequestBody ArtistsAndGroupsOnEventInputDto artistsAndGroupsOnEventInputDto){
        log.info("request for creating artistsAndGroupsOnEvent from data source {}",
                artistsAndGroupsOnEventInputDto);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                artistsAndGroupsOnEventInputDto.getArtistPageId());
        ResponseEntity<Event> eventResponseEntity = eventController.getOne(
                artistsAndGroupsOnEventInputDto.getEventId());
        ArtistsAndGroupsOnEvent artistsAndGroupsOnEvent = new ArtistsAndGroupsOnEvent(
                artistPageResponseEntity.getBody(),
                eventResponseEntity.getBody(),
                artistsAndGroupsOnEventInputDto.getPerformanceTime(),
                artistsAndGroupsOnEventInputDto.getIncome());

        log.info("request for creating artistsAndGroupsOnEvent with parameters {}", artistsAndGroupsOnEvent);

        return new ResponseEntity<>(artistsAndGroupsOnEventRepo.save(artistsAndGroupsOnEvent), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующей странице исполнителя")
    public ResponseEntity<ArtistsAndGroupsOnEvent> update(@PathVariable("id") long id,
                                       @Valid @RequestBody ArtistsAndGroupsOnEventInputDto artistsAndGroupsOnEventInputDto){

        log.info("request for updating artistsAndGroupsOnEvent by id {} with parameters {}",
                id, artistsAndGroupsOnEventInputDto);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                artistsAndGroupsOnEventInputDto.getArtistPageId());
        ResponseEntity<Event> eventResponseEntity = eventController.getOne(
                artistsAndGroupsOnEventInputDto.getEventId());
        ArtistsAndGroupsOnEvent artistsAndGroupsOnEvent = new ArtistsAndGroupsOnEvent(
                artistPageResponseEntity.getBody(),
                eventResponseEntity.getBody(),
                artistsAndGroupsOnEventInputDto.getPerformanceTime(),
                artistsAndGroupsOnEventInputDto.getIncome());
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
