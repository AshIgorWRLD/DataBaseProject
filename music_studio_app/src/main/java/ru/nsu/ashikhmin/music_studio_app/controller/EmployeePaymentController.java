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
import ru.nsu.ashikhmin.music_studio_app.entity.*;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.dto.EmployeePaymentInputDto;
import ru.nsu.ashikhmin.music_studio_app.repository.EmployeePaymentRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Validated
@RestController
@RequestMapping("payments/employee")
@Api(description = "Контроллер выплат работникам")
public class EmployeePaymentController {

    private final EmployeePaymentRepo employeePaymentRepo;

    private final EmployeeController employeeController;
    private final EmployeeBillController employeeBillController;

    @Autowired
    public EmployeePaymentController(EmployeePaymentRepo employeePaymentRepo,
                            EmployeeController employeeController,
                            EmployeeBillController employeeBillController){
        this.employeePaymentRepo = employeePaymentRepo;
        this.employeeController = employeeController;
        this.employeeBillController = employeeBillController;
    }

    @GetMapping
    @ApiOperation("Получение списка выплат работникам")
    public ResponseEntity<List<EmployeePayment>> list(){
        log.info("request for getting all employeePayments");
        List<EmployeePayment> employeePayments = employeePaymentRepo.findAll();
        if(employeePayments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employeePayments, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение выплаты работнику по id")
    public ResponseEntity<EmployeePayment> getOne(@PathVariable("id") Long id) {
        log.info("request for getting employeePayment with id: {}", id);

        EmployeePayment employeePayment = employeePaymentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found employeePayment with id = " + id));

        return new ResponseEntity<>(employeePayment, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание новой выплаты работнику")
    public ResponseEntity<EmployeePayment> create(@Valid @RequestBody EmployeePaymentInputDto employeePaymentInputDto){
        log.info("request for creating employeePayment from data source {}", employeePaymentInputDto);
        ResponseEntity<Employee> employeeResponseEntity = employeeController.getOne(
                employeePaymentInputDto.getEmployeeId());
        ResponseEntity<EmployeeBill> employeeBillResponseEntity = employeeBillController.getOne(
                employeePaymentInputDto.getEmployeeBillId());
        EmployeeBill employeeBill = employeeBillResponseEntity.getBody();
        log.info("employee bill {}", employeeBill);
        Set<EmployeeBill> set = new HashSet<>();
        set.add(employeeBill);
        set.forEach(x->log.info("set {}", x));
        EmployeePayment employeePayment = new EmployeePayment(employeeResponseEntity.getBody(),
                set, employeePaymentInputDto.getAmount());
        employeePayment.setEmployeeBillId(employeeBill.getId());
        log.info("request for creating employeePayment with parameters {}", employeePayment);
        return new ResponseEntity<>(employeePaymentRepo.save(employeePayment), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующей выплате работнику")
    public ResponseEntity<EmployeePayment> update(@PathVariable("id") long id,
                                         @Valid @RequestBody EmployeePaymentInputDto employeePaymentInputDto){

        log.info("request for updating employeePayment by id {} with parameters {}",
                id, employeePaymentInputDto);
        ResponseEntity<Employee> employeeResponseEntity = employeeController.getOne(
                employeePaymentInputDto.getEmployeeId());
        ResponseEntity<EmployeeBill> employeeBillResponseEntity = employeeBillController.getOne(
                employeePaymentInputDto.getEmployeeBillId());
        EmployeeBill employeeBill = employeeBillResponseEntity.getBody();
        EmployeePayment employeePayment = new EmployeePayment(employeeResponseEntity.getBody(),
                null, employeePaymentInputDto.getAmount());
        EmployeePayment employeePaymentFromDataBase = employeePaymentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found employeePayment with id = " + id));
        log.info("EmployeePayment from data base: " + employeePaymentFromDataBase);
        employeePaymentFromDataBase.getEmployeeBills().add(employeeBill);
        BeanUtils.copyProperties(employeePayment, employeePaymentFromDataBase,
                NullProperty.getNullPropertiesString(employeePayment));
        return new ResponseEntity<>(employeePaymentRepo.save(employeePaymentFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление выплаты работнику")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") EmployeePayment employeePayment) {

        log.info("request for deleting employeePayment with parameters {}", employeePayment);

        employeePaymentRepo.delete(employeePayment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех выплат работникам")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all employeePayments");
        employeePaymentRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
