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
import ru.nsu.ashikhmin.music_studio_app.entity.InvestorBill;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.repository.InvestorBillRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("bills/investor")
@Api(description = "Контроллер счетов инвесторов")
public class InvestorBillController {


    private final InvestorBillRepo investorBillRepo;

    @Autowired
    public InvestorBillController(InvestorBillRepo investorBillRepo) {
        this.investorBillRepo = investorBillRepo;
    }

    @GetMapping
    @ApiOperation("Получение списка счетов инвесторов")
    public ResponseEntity<Page<InvestorBill>> list(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        log.info("request for getting all investorBills");
        Page<InvestorBill> investorBills = investorBillRepo.findAll(pageable);
        if(investorBills.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(investorBills, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение счетов инвесторов по id")
    public ResponseEntity<InvestorBill> getOne(@PathVariable("id") Long id) {
        log.info("request for getting investorBill with id: {}", id);

        InvestorBill person = investorBillRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found investorBill with id = " + id));

        log.info("{}",person);

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового счета инвестора")
    public ResponseEntity<InvestorBill> create(@Valid @RequestBody InvestorBill investorBill){
        log.info("request for creating person with parameters {}", investorBill);

        return new ResponseEntity<>(investorBillRepo.save(investorBill), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем счете инвестора")
    public ResponseEntity<InvestorBill> update(@PathVariable("id") long id,
                                               @Valid @RequestBody InvestorBill investorBill){
        log.info("request for updating investorBill by id {} with parameters {}",
                id, investorBill);

        InvestorBill personFromDataBase = investorBillRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found investorBill with id = " + id));
        log.info("InvestorBill from data base: " + personFromDataBase);

        BeanUtils.copyProperties(investorBill, personFromDataBase,
                NullProperty.getNullPropertiesString(investorBill));
        return new ResponseEntity<>(investorBillRepo.save(personFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление счета инвестора")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") InvestorBill investorBill) {

        log.info("request for deleting investorBill with parameters {}", investorBill);

        investorBillRepo.delete(investorBill);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех счетов инвесторов")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all investorBills");
        investorBillRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
