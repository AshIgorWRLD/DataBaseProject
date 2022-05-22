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
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.repository.EventRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("events")
@Api(description = "Контроллер мероприятий")
public class EventController {

    private final EventRepo eventRepo;

    @Autowired
    public EventController(EventRepo eventRepo){
        this.eventRepo = eventRepo;
    }

    @GetMapping
    @ApiOperation("Получение списка пользователей")
    public ResponseEntity<List<Event>> list(){
        log.info("request for getting all events");
        List<Event> events = eventRepo.findAll();
        if(events.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение пользователя по id")
    public ResponseEntity<Event> getOne(@PathVariable("id") Long id) {
        log.info("request for getting event with id: {}", id);

        Event person = eventRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found event with id = " + id));

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового пользователя")
    public ResponseEntity<Event> create(@Valid @RequestBody Event event){
        log.info("request for creating person with parameters {}", event);

        return new ResponseEntity<>(eventRepo.save(event), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем пользователе")
    public ResponseEntity<Event> update(@PathVariable("id") long id,
                                       @Valid @RequestBody Event event){
        log.info("request for updating event by id {} with parameters {}",
                id, event);

        Event personFromDataBase = eventRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found event with id = " + id));
        log.info("Event from data base: " + personFromDataBase);

        BeanUtils.copyProperties(event, personFromDataBase,
                NullProperty.getNullPropertiesString(event));
        return new ResponseEntity<>(eventRepo.save(personFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление пользователя")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Event event) {

        log.info("request for deleting event with parameters {}", event);

        eventRepo.delete(event);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех пользователей")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all events");
        eventRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
