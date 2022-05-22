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
import ru.nsu.ashikhmin.music_studio_app.entity.Event;
import ru.nsu.ashikhmin.music_studio_app.entity.Sponsor;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.postdatasource.SponsorDataSource;
import ru.nsu.ashikhmin.music_studio_app.repository.SponsorRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("events/sponsors")
@Api(description = "Контроллер спонсоров")
public class SponsorController {

    private final SponsorRepo sponsorRepo;

    private final EventController eventController;

    @Autowired
    public SponsorController(SponsorRepo sponsorRepo, EventController eventController){
        this.sponsorRepo = sponsorRepo;
        this.eventController = eventController;
    }

    @GetMapping
    @ApiOperation("Получение списка инвесторов")
    public ResponseEntity<List<Sponsor>> list(){
        log.info("request for getting all sponsors");
        List<Sponsor> sponsors = sponsorRepo.findAll();
        if(sponsors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(sponsors, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение инвестора по id")
    public ResponseEntity<Sponsor> getOne(@PathVariable("id") Long id) {
        log.info("request for getting sponsor with id: {}", id);

        Sponsor sponsor = sponsorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found sponsor with id = " + id));

        return new ResponseEntity<>(sponsor, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового инвестора")
    public ResponseEntity<Sponsor> create(@Valid @RequestBody SponsorDataSource sponsorDataSource){
        log.info("request for creating sponsor from data source {}", sponsorDataSource);
        ResponseEntity<Event> eventResponseEntity = eventController.getOne(
                sponsorDataSource.getEventId());
        Sponsor sponsor = new Sponsor(eventResponseEntity.getBody(),
                sponsorDataSource.getName(), sponsorDataSource.getBusinessType(),
                sponsorDataSource.getSponsoredMoney());

        log.info("request for creating sponsor with parameters {}", sponsor);

        return new ResponseEntity<>(sponsorRepo.save(sponsor), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем инвесторе")
    public ResponseEntity<Sponsor> update(@PathVariable("id") long id,
                                           @Valid @RequestBody SponsorDataSource sponsorDataSource){

        log.info("request for updating sponsor by id {} with parameters {}",
                id, sponsorDataSource);
        ResponseEntity<Event> eventResponseEntity = eventController.getOne(
                sponsorDataSource.getEventId());
        Sponsor sponsor = new Sponsor(eventResponseEntity.getBody(),
                sponsorDataSource.getName(), sponsorDataSource.getBusinessType(),
                sponsorDataSource.getSponsoredMoney());
        Sponsor sponsorFromDataBase = sponsorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found sponsor with id = " + id));
        log.info("Sponsor from data base: " + sponsorFromDataBase);

        BeanUtils.copyProperties(sponsor, sponsorFromDataBase,
                NullProperty.getNullPropertiesString(sponsor));
        return new ResponseEntity<>(sponsorRepo.save(sponsorFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление инвестора")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Sponsor sponsor) {

        log.info("request for deleting sponsor with parameters {}", sponsor);

        sponsorRepo.delete(sponsor);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех инвесторов")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all sponsors");
        sponsorRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
