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
import ru.nsu.ashikhmin.music_studio_app.entity.Employee;
import ru.nsu.ashikhmin.music_studio_app.entity.User;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.postdatasource.EmployeeDataSource;
import ru.nsu.ashikhmin.music_studio_app.repository.EmployeeRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("users/employees")
@Api(description = "Контроллер работников")
public class EmployeeController {

    private final EmployeeRepo employeeRepo;

    private final UserController userController;

    @Autowired
    public EmployeeController(EmployeeRepo employeeRepo, UserController userController){
        this.employeeRepo = employeeRepo;
        this.userController = userController;
    }

    @GetMapping
    @ApiOperation("Получение списка работников")
    public ResponseEntity<List<Employee>> list(){
        log.info("request for getting all employees");
        List<Employee> employees = employeeRepo.findAll();
        if(employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение работника по id")
    public ResponseEntity<Employee> getOne(@PathVariable("id") Long id) {
        log.info("request for getting employee with id: {}", id);

        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found employee with id = " + id));

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового работника")
    public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeDataSource employeeDataSource){
        log.info("request for creating employee from data source {}", employeeDataSource);
        ResponseEntity<User> userResponseEntity = userController.getOne(employeeDataSource.getUserId());
        Employee employee = new Employee(userResponseEntity.getBody(),
                employeeDataSource.getPost());

        log.info("request for creating employee with parameters {}", employee);

        return new ResponseEntity<>(employeeRepo.save(employee), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем работнике")
    public ResponseEntity<Employee> update(@PathVariable("id") long id,
                                         @Valid @RequestBody EmployeeDataSource employeeDataSource){

        log.info("request for updating employee by id {} with parameters {}",
                id, employeeDataSource);
        ResponseEntity<User> userResponseEntity = userController.getOne(employeeDataSource.getUserId());
        Employee employee = new Employee(userResponseEntity.getBody(),
                employeeDataSource.getPost());

        Employee employeeFromDataBase = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found employee with id = " + id));
        log.info("Employee from data base: " + employeeFromDataBase);

        BeanUtils.copyProperties(employee, employeeFromDataBase,
                NullProperty.getNullPropertiesString(employee));
        return new ResponseEntity<>(employeeRepo.save(employeeFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление работника")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Employee employee) {

        log.info("request for deleting employee with parameters {}", employee);

        employeeRepo.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех работников")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all employees");
        employeeRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
