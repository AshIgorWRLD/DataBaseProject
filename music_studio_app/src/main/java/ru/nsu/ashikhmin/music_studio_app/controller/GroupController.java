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
import ru.nsu.ashikhmin.music_studio_app.entity.Group;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.repository.GroupRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("groups")
@Api(description = "Контроллер групп")
public class GroupController {

    private final GroupRepo groupRepo;

    @Autowired
    public GroupController(GroupRepo groupRepo){
        this.groupRepo = groupRepo;
    }

    @GetMapping
    @ApiOperation("Получение списка групп")
    public ResponseEntity<Page<Group>> list(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        log.info("request for getting all groups");
        Page<Group> groups = groupRepo.findAll(pageable);
        if(groups.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение группы по id")
    public ResponseEntity<Group> getOne(@PathVariable("id") Long id) {
        log.info("request for getting group with id: {}", id);

        Group person = groupRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found group with id = " + id));

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    @ApiOperation("Получение группы по имени")
    public ResponseEntity<Group> getOne(@PathVariable("name") String name) {
        log.info("request for getting group with name: {}", name);

        Group group = groupRepo.findByName(name);

        if (group == null){
            throw new ResourceNotFoundException("Not found group with name = " + name);
        }

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание новой группы")
    public ResponseEntity<Group> create(@Valid @RequestBody Group group){
        log.info("request for creating group with parameters {}", group);

        return new ResponseEntity<>(groupRepo.save(group), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующей группе")
    public ResponseEntity<Group> update(@PathVariable("id") long id,
                                       @Valid @RequestBody Group group){
        log.info("request for updating group by id {} with parameters {}",
                id, group);

        Group personFromDataBase = groupRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found group with id = " + id));
        log.info("Group from data base: " + personFromDataBase);

        BeanUtils.copyProperties(group, personFromDataBase,
                NullProperty.getNullPropertiesString(group));
        return new ResponseEntity<>(groupRepo.save(personFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление группы")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Group group) {

        log.info("request for deleting group with parameters {}", group);

        groupRepo.delete(group);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех групп")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all groups");
        groupRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
