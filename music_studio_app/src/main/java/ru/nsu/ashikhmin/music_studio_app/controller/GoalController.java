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
import ru.nsu.ashikhmin.music_studio_app.entity.Goal;
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistPage;
import ru.nsu.ashikhmin.music_studio_app.entity.Progress;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.postdatasource.GoalDataSource;
import ru.nsu.ashikhmin.music_studio_app.repository.GoalRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("artist_pages/goals")
@Api(description = "Контроллер целей")
public class GoalController {

    private final GoalRepo goalRepo;

    private final ArtistPageController artistPageController;
    private final ProgressController progressController;

    @Autowired
    public GoalController(GoalRepo goalRepo, ArtistPageController artistPageController,
                          ProgressController progressController){
        this.goalRepo = goalRepo;
        this.artistPageController = artistPageController;
        this.progressController = progressController;
    }

    @GetMapping
    @ApiOperation("Получение списка страниц исполнителей")
    public ResponseEntity<List<Goal>> list(){
        log.info("request for getting all goals");
        List<Goal> goals = goalRepo.findAll();
        if(goals.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(goals, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение страницы исполнителя по id")
    public ResponseEntity<Goal> getOne(@PathVariable("id") Long id) {
        log.info("request for getting goal with id: {}", id);

        Goal goal = goalRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found goal with id = " + id));

        return new ResponseEntity<>(goal, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание новой страницы исполнителя")
    public ResponseEntity<Goal> create(@Valid @RequestBody GoalDataSource goalDataSource){
        log.info("request for creating goal from data source {}",
                goalDataSource);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                goalDataSource.getArtistPageId());
        ResponseEntity<Progress> progressResponseEntity = progressController.getOne(
                goalDataSource.getProgressId());
        Goal goal = new Goal(
                artistPageResponseEntity.getBody(),
                progressResponseEntity.getBody(),
                goalDataSource.getProgressPercentage(),
                goalDataSource.getStatement(),
                goalDataSource.getType(),
                goalDataSource.getDeadline(),
                goalDataSource.getResources());

        log.info("request for creating goal with parameters {}", goal);

        return new ResponseEntity<>(goalRepo.save(goal), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующей странице исполнителя")
    public ResponseEntity<Goal> update(@PathVariable("id") long id,
                                                       @Valid @RequestBody GoalDataSource goalDataSource){

        log.info("request for updating goal by id {} with parameters {}",
                id, goalDataSource);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                goalDataSource.getArtistPageId());
        ResponseEntity<Progress> progressResponseEntity = progressController.getOne(
                goalDataSource.getProgressId());
        Goal goal = new Goal(
                artistPageResponseEntity.getBody(),
                progressResponseEntity.getBody(),
                goalDataSource.getProgressPercentage(),
                goalDataSource.getStatement(),
                goalDataSource.getType(),
                goalDataSource.getDeadline(),
                goalDataSource.getResources());
        Goal goalFromDataBase = goalRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found goal with id = " + id));
        log.info("Goal from data base: " + goalFromDataBase);
        BeanUtils.copyProperties(goal, goalFromDataBase,
                NullProperty.getNullPropertiesString(goal));
        return new ResponseEntity<>(goalRepo.save(goalFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление страницы исполнителя")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Goal goal) {

        log.info("request for deleting goal with parameters {}", goal);

        goalRepo.delete(goal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех страниц исполнителей")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all goals");
        goalRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}