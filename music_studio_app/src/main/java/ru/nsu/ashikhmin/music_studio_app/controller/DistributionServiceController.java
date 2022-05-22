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
import ru.nsu.ashikhmin.music_studio_app.entity.DistributionService;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.repository.DistributionServiceRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("distribution/services")
@Api(description = "Контроллер сервисов дистрибуции")
public class DistributionServiceController {

    private final DistributionServiceRepo distributionServiceRepo;

    @Autowired
    public DistributionServiceController(DistributionServiceRepo distributionServiceRepo){
        this.distributionServiceRepo = distributionServiceRepo;
    }

    @GetMapping
    @ApiOperation("Получение списка сервисов дистрибуции")
    public ResponseEntity<List<DistributionService>> list(){
        log.info("request for getting all distributionServices");
        List<DistributionService> distributionServices = distributionServiceRepo.findAll();
        if(distributionServices.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(distributionServices, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение сервиса дистрибуции по id")
    public ResponseEntity<DistributionService> getOne(@PathVariable("id") Long id) {
        log.info("request for getting distributionService with id: {}", id);

        DistributionService person = distributionServiceRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found distributionService with id = " + id));

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового сервиса дистрибуции")
    public ResponseEntity<DistributionService> create(@Valid @RequestBody DistributionService distributionService){
        log.info("request for creating person with parameters {}", distributionService);

        return new ResponseEntity<>(distributionServiceRepo.save(distributionService), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем сервисе дистрибуции")
    public ResponseEntity<DistributionService> update(@PathVariable("id") long id,
                                       @Valid @RequestBody DistributionService distributionService){
        log.info("request for updating distributionService by id {} with parameters {}",
                id, distributionService);

        DistributionService personFromDataBase = distributionServiceRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found distributionService with id = " + id));
        log.info("DistributionService from data base: " + personFromDataBase);

        BeanUtils.copyProperties(distributionService, personFromDataBase,
                NullProperty.getNullPropertiesString(distributionService));
        return new ResponseEntity<>(distributionServiceRepo.save(personFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление сервиса дистрибуции")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") DistributionService distributionService) {

        log.info("request for deleting distributionService with parameters {}", distributionService);

        distributionServiceRepo.delete(distributionService);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех сервисов дистрибуции")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all distributionServices");
        distributionServiceRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
