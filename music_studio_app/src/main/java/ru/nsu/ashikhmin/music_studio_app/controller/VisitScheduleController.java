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
import ru.nsu.ashikhmin.music_studio_app.additionalmodels.AllVisitScheduleForOne;
import ru.nsu.ashikhmin.music_studio_app.additionalmodels.VisitScheduleWithoutClient;
import ru.nsu.ashikhmin.music_studio_app.entity.Client;
import ru.nsu.ashikhmin.music_studio_app.entity.VisitSchedule;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.postdatasource.VisitScheduleDataSource;
import ru.nsu.ashikhmin.music_studio_app.repository.VisitScheduleRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("users/clients/schedule")
@Api(description = "Контроллер расписания клиентов")
public class VisitScheduleController {

    private final VisitScheduleRepo visitScheduleRepo;

    private final ClientController clientController;

    @Autowired
    public VisitScheduleController(VisitScheduleRepo visitScheduleRepo,
                                  ClientController clientController){
        this.visitScheduleRepo = visitScheduleRepo;
        this.clientController = clientController;
    }

    @GetMapping
    @ApiOperation("Получение списка расписаний работников")
    public ResponseEntity<List<VisitSchedule>> list(){
        log.info("request for getting all visitSchedules");
        List<VisitSchedule> visitSchedules = visitScheduleRepo.findAll();
        if(visitSchedules.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(visitSchedules, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    @ApiOperation("Получение расписания работников по id")
    public ResponseEntity<VisitSchedule> getOne(@PathVariable("id") Long id) {
        log.info("request for getting visitSchedule with id: {}", id);

        VisitSchedule visitSchedule = visitScheduleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found visitSchedule with id = " + id));

        return new ResponseEntity<>(visitSchedule, HttpStatus.OK);
    }

    @GetMapping("client/{id}")
    @ApiOperation("Получение расписания работника по id работника")
    public ResponseEntity<AllVisitScheduleForOne> getAllByEmployeeId(@PathVariable("id") Long id) {
        log.info("request for getting visitSchedule with id: {}", id);

        List<VisitSchedule> visitSchedule = visitScheduleRepo.findByClientId(id);
        if(visitSchedule == null) {
            throw new ResourceNotFoundException(
                    "Not found visitSchedule with id = " + id);
        }
        List<VisitScheduleWithoutClient> schedule = new ArrayList<>();
        Client client = visitSchedule.get(0).getClient();
        visitSchedule.forEach(x -> {
            schedule.add(new VisitScheduleWithoutClient(x.getVisitDate(), x.getTiming(),
                    x.getLengthOfVisit(), x.getType()));
        });

        return new ResponseEntity<>(new AllVisitScheduleForOne(client, schedule),
                HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового расписания работников")
    public ResponseEntity<VisitSchedule> create(@Valid @RequestBody VisitScheduleDataSource visitScheduleDataSource){
        log.info("request for creating visitSchedule from data source {}", visitScheduleDataSource);
        ResponseEntity<Client> employeeResponseEntity = clientController.getOne(
                visitScheduleDataSource.getClientId());
        VisitSchedule visitSchedule = new VisitSchedule(employeeResponseEntity.getBody(),
                visitScheduleDataSource.getVisitDate(), visitScheduleDataSource.getTiming(),
                visitScheduleDataSource.getLengthOfVisit(), visitScheduleDataSource.getType());
        log.info("request for creating visitSchedule with parameters {}", visitSchedule);
        return new ResponseEntity<>(visitScheduleRepo.save(visitSchedule), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем расписании работников")
    public ResponseEntity<VisitSchedule> update(@PathVariable("id") long id,
                                               @Valid @RequestBody VisitScheduleDataSource visitScheduleDataSource){

        log.info("request for updating visitSchedule by id {} with parameters {}",
                id, visitScheduleDataSource);
        ResponseEntity<Client> employeeResponseEntity = clientController.getOne(
                visitScheduleDataSource.getClientId());
        VisitSchedule visitSchedule = new VisitSchedule(employeeResponseEntity.getBody(),
                visitScheduleDataSource.getVisitDate(), visitScheduleDataSource.getTiming(),
                visitScheduleDataSource.getLengthOfVisit(), visitScheduleDataSource.getType());
        VisitSchedule visitScheduleFromDataBase = visitScheduleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found visitSchedule with id = " + id));
        log.info("VisitSchedule from data base: " + visitScheduleFromDataBase);
        BeanUtils.copyProperties(visitSchedule, visitScheduleFromDataBase,
                NullProperty.getNullPropertiesString(visitSchedule));
        return new ResponseEntity<>(visitScheduleRepo.save(visitScheduleFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление расписания работников")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") VisitSchedule visitSchedule) {

        log.info("request for deleting visitSchedule with parameters {}", visitSchedule);

        visitScheduleRepo.delete(visitSchedule);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех расписаний работников")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all visitSchedules");
        visitScheduleRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
