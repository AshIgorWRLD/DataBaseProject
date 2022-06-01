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
import ru.nsu.ashikhmin.music_studio_app.entity.EmployeeBill;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.repository.EmployeeBillRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("bills/employee")
@Api(description = "Контроллер счетов работников")
public class EmployeeBillController {
    
    private final EmployeeBillRepo employeeBillRepo;

    @Autowired
    public EmployeeBillController(EmployeeBillRepo employeeBillRepo) {
        this.employeeBillRepo = employeeBillRepo;
    }

    @GetMapping
    @ApiOperation("Получение списка счетов работников")
    public ResponseEntity<Page<EmployeeBill>> list(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        log.info("request for getting all employeeBills");
        Page<EmployeeBill> employeeBills = employeeBillRepo.findAll(pageable);
        if(employeeBills.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employeeBills, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение счетов работников по id")
    public ResponseEntity<EmployeeBill> getOne(@PathVariable("id") Long id) {
        log.info("request for getting employeeBill with id: {}", id);

        EmployeeBill person = employeeBillRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found employeeBill with id = " + id));

        log.info("{}",person);

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового счета работника")
    public ResponseEntity<EmployeeBill> create(@Valid @RequestBody EmployeeBill employeeBill){
        log.info("request for creating person with parameters {}", employeeBill);

        return new ResponseEntity<>(employeeBillRepo.save(employeeBill), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем счете работника")
    public ResponseEntity<EmployeeBill> update(@PathVariable("id") long id,
                                       @Valid @RequestBody EmployeeBill employeeBill){
        log.info("request for updating employeeBill by id {} with parameters {}",
                id, employeeBill);

        EmployeeBill personFromDataBase = employeeBillRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found employeeBill with id = " + id));
        log.info("EmployeeBill from data base: " + personFromDataBase);

        BeanUtils.copyProperties(employeeBill, personFromDataBase,
                NullProperty.getNullPropertiesString(employeeBill));
        return new ResponseEntity<>(employeeBillRepo.save(personFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление счета работника")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") EmployeeBill employeeBill) {

        log.info("request for deleting employeeBill with parameters {}", employeeBill);

        employeeBillRepo.delete(employeeBill);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех счетов работников")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all employeeBills");
        employeeBillRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
