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
import ru.nsu.ashikhmin.music_studio_app.entity.Investor;
import ru.nsu.ashikhmin.music_studio_app.entity.InvestorBill;
import ru.nsu.ashikhmin.music_studio_app.entity.InvestorPayment;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.dto.InvestorPaymentInputDto;
import ru.nsu.ashikhmin.music_studio_app.repository.InvestorPaymentRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Validated
@RestController
@RequestMapping("payments/investor")
@Api(description = "Контроллер выплат инвесторам")
public class InvestorPaymentController {

    private final InvestorPaymentRepo investorPaymentRepo;

    private final InvestorController investorController;
    private final InvestorBillController investorBillController;

    @Autowired
    public InvestorPaymentController(InvestorPaymentRepo investorPaymentRepo,
                                     InvestorController investorController,
                                     InvestorBillController investorBillController){
        this.investorPaymentRepo = investorPaymentRepo;
        this.investorController = investorController;
        this.investorBillController = investorBillController;
    }

    @GetMapping
    @ApiOperation("Получение списка выплат инвесторам")
    public ResponseEntity<List<InvestorPayment>> list(){
        log.info("request for getting all investorPayments");
        List<InvestorPayment> investorPayments = investorPaymentRepo.findAll();
        if(investorPayments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(investorPayments, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение выплаты инвестору по id")
    public ResponseEntity<InvestorPayment> getOne(@PathVariable("id") Long id) {
        log.info("request for getting investorPayment with id: {}", id);

        InvestorPayment investorPayment = investorPaymentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found investorPayment with id = " + id));

        return new ResponseEntity<>(investorPayment, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание новой выплаты инвестору")
    public ResponseEntity<InvestorPayment> create(@Valid @RequestBody InvestorPaymentInputDto investorPaymentInputDto){
        log.info("request for creating investorPayment from data source {}", investorPaymentInputDto);
        ResponseEntity<Investor> investorResponseEntity = investorController.getOne(
                investorPaymentInputDto.getInvestorId());
        ResponseEntity<InvestorBill> investorBillResponseEntity = investorBillController.getOne(
                investorPaymentInputDto.getInvestorBillId());
        InvestorBill investorBill = investorBillResponseEntity.getBody();
        log.info("investor bill {}", investorBill);
        Set<InvestorBill> set = new HashSet<>();
        set.add(investorBill);
        set.forEach(x->log.info("set {}", x));
        InvestorPayment investorPayment = new InvestorPayment(investorResponseEntity.getBody(),
                set, investorPaymentInputDto.getAmount());
        investorPayment.setInvestorBillId(investorBill.getId());
        log.info("request for creating investorPayment with parameters {}", investorPayment);
        return new ResponseEntity<>(investorPaymentRepo.save(investorPayment), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующей выплате инвестору")
    public ResponseEntity<InvestorPayment> update(@PathVariable("id") long id,
                                                  @Valid @RequestBody InvestorPaymentInputDto investorPaymentInputDto){

        log.info("request for updating investorPayment by id {} with parameters {}",
                id, investorPaymentInputDto);
        ResponseEntity<Investor> investorResponseEntity = investorController.getOne(
                investorPaymentInputDto.getInvestorId());
        ResponseEntity<InvestorBill> investorBillResponseEntity = investorBillController.getOne(
                investorPaymentInputDto.getInvestorBillId());
        InvestorBill investorBill = investorBillResponseEntity.getBody();
        InvestorPayment investorPayment = new InvestorPayment(investorResponseEntity.getBody(),
                null, investorPaymentInputDto.getAmount());
        InvestorPayment investorPaymentFromDataBase = investorPaymentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found investorPayment with id = " + id));
        log.info("InvestorPayment from data base: " + investorPaymentFromDataBase);
        investorPaymentFromDataBase.getInvestorBills().add(investorBill);
        BeanUtils.copyProperties(investorPayment, investorPaymentFromDataBase,
                NullProperty.getNullPropertiesString(investorPayment));
        return new ResponseEntity<>(investorPaymentRepo.save(investorPaymentFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление выплаты инвестору")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") InvestorPayment investorPayment) {

        log.info("request for deleting investorPayment with parameters {}", investorPayment);

        investorPaymentRepo.delete(investorPayment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех выплат инвесторам")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all investorPayments");
        investorPaymentRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
