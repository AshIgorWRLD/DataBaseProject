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
import ru.nsu.ashikhmin.music_studio_app.additionalmodels.AllWorkScheduleForOne;
import ru.nsu.ashikhmin.music_studio_app.additionalmodels.WorkScheduleWithoutEmployee;
import ru.nsu.ashikhmin.music_studio_app.entity.Employee;
import ru.nsu.ashikhmin.music_studio_app.entity.WorkSchedule;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.postdatasource.WorkScheduleDataSource;
import ru.nsu.ashikhmin.music_studio_app.repository.WorkScheduleRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("users/employees/schedule")
@Api(description = "Контроллер расписания работников")
public class WorkScheduleController{

    private final WorkScheduleRepo workScheduleRepo;

    private final EmployeeController employeeController;

    @Autowired
    public WorkScheduleController(WorkScheduleRepo workScheduleRepo,
                                     EmployeeController employeeController){
        this.workScheduleRepo = workScheduleRepo;
        this.employeeController = employeeController;
    }

    @GetMapping
    @ApiOperation("Получение списка расписаний работников")
    public ResponseEntity<List<WorkSchedule>> list(){
        log.info("request for getting all workSchedules");
        List<WorkSchedule> workSchedules = workScheduleRepo.findAll();
        if(workSchedules.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(workSchedules, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    @ApiOperation("Получение расписания работников по id")
    public ResponseEntity<WorkSchedule> getOne(@PathVariable("id") Long id) {
        log.info("request for getting workSchedule with id: {}", id);

        WorkSchedule workSchedule = workScheduleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found workSchedule with id = " + id));

        return new ResponseEntity<>(workSchedule, HttpStatus.OK);
    }

    @GetMapping("employee/{id}")
    @ApiOperation("Получение расписания работника по id работника")
    public ResponseEntity<AllWorkScheduleForOne> getAllByEmployeeId(@PathVariable("id") Long id) {
        log.info("request for getting workSchedule with id: {}", id);

        List<WorkSchedule> workSchedule = workScheduleRepo.findByEmployeeId(id);
        if(workSchedule == null) {
            throw new ResourceNotFoundException(
                    "Not found workSchedule with id = " + id);
        }
        List<WorkScheduleWithoutEmployee> schedule = new ArrayList<>();
        Employee employee = workSchedule.get(0).getEmployee();
        workSchedule.forEach(x -> {
            schedule.add(new WorkScheduleWithoutEmployee(x.getWeekDay(), x.getTimeToCome(),
                    x.getWorkLength()));
        });

        return new ResponseEntity<>(new AllWorkScheduleForOne(employee, schedule),
                HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового расписания работников")
    public ResponseEntity<WorkSchedule> create(@Valid @RequestBody WorkScheduleDataSource workScheduleDataSource){
        log.info("request for creating workSchedule from data source {}", workScheduleDataSource);
        ResponseEntity<Employee> employeeResponseEntity = employeeController.getOne(
                workScheduleDataSource.getEmployeeId());
        WorkSchedule workSchedule = new WorkSchedule(employeeResponseEntity.getBody(),
                workScheduleDataSource.getWeekDay(), workScheduleDataSource.getTimeToCome(),
                workScheduleDataSource.getWorkLength());
        log.info("request for creating workSchedule with parameters {}", workSchedule);
        return new ResponseEntity<>(workScheduleRepo.save(workSchedule), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем расписании работников")
    public ResponseEntity<WorkSchedule> update(@PathVariable("id") long id,
                                                  @Valid @RequestBody WorkScheduleDataSource workScheduleDataSource){

        log.info("request for updating workSchedule by id {} with parameters {}",
                id, workScheduleDataSource);
        ResponseEntity<Employee> employeeResponseEntity = employeeController.getOne(
                workScheduleDataSource.getEmployeeId());
        WorkSchedule workSchedule = new WorkSchedule(employeeResponseEntity.getBody(),
                workScheduleDataSource.getWeekDay(), workScheduleDataSource.getTimeToCome(),
                workScheduleDataSource.getWorkLength());
        WorkSchedule workScheduleFromDataBase = workScheduleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found workSchedule with id = " + id));
        log.info("WorkSchedule from data base: " + workScheduleFromDataBase);
        BeanUtils.copyProperties(workSchedule, workScheduleFromDataBase,
                NullProperty.getNullPropertiesString(workSchedule));
        return new ResponseEntity<>(workScheduleRepo.save(workScheduleFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление расписания работников")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") WorkSchedule workSchedule) {

        log.info("request for deleting workSchedule with parameters {}", workSchedule);

        workScheduleRepo.delete(workSchedule);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех расписаний работников")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all workSchedules");
        workScheduleRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
