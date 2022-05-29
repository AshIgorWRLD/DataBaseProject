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
import ru.nsu.ashikhmin.music_studio_app.dto.InvestmentInputDto;
import ru.nsu.ashikhmin.music_studio_app.repository.InvestmentRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Validated
@RestController
@RequestMapping("investments")
@Api(description = "Контроллер инвестиций")
public class InvestmentController {

    private final InvestmentRepo investmentRepo;

    private final ArtistPageController artistPageController;
    private final InvestorController investorController;

    @Autowired
    public InvestmentController(InvestmentRepo investmentRepo,
                                     ArtistPageController artistPageController,
                                     InvestorController investorController){
        this.investmentRepo = investmentRepo;
        this.artistPageController = artistPageController;
        this.investorController = investorController;
    }

    @GetMapping
    @ApiOperation("Получение списка инвестиций")
    public ResponseEntity<List<Investment>> list(){
        log.info("request for getting all investments");
        List<Investment> investments = investmentRepo.findAll();
        if(investments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(investments, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение инвестиции по id")
    public ResponseEntity<Investment> getOne(@PathVariable("id") Long id) {
        log.info("request for getting investment with id: {}", id);

        Investment investment = investmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found investment with id = " + id));

        return new ResponseEntity<>(investment, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание новой инвестиции")
    public ResponseEntity<Investment> create(@Valid @RequestBody InvestmentInputDto investmentInputDto){
        log.info("request for creating investment from data source {}", investmentInputDto);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                investmentInputDto.getRecipientId());
        ResponseEntity<Investor> investorResponseEntity = investorController.getOne(
                investmentInputDto.getInvestorId());
        ArtistPage recipient = artistPageResponseEntity.getBody();
        log.info("recipient {}", recipient);
        Set<ArtistPage> set = new HashSet<>();
        set.add(recipient);
        set.forEach(x->log.info("set {}", x));
        Investment investment = new Investment(investorResponseEntity.getBody(),
                set, investmentInputDto.getMoneyAmount());
        log.info("request for creating investment with parameters {}", investment);
        return new ResponseEntity<>(investmentRepo.save(investment), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующей инвестиции")
    public ResponseEntity<Investment> update(@PathVariable("id") long id,
                                                  @Valid @RequestBody InvestmentInputDto investmentInputDto){

        log.info("request for updating investment by id {} with parameters {}",
                id, investmentInputDto);
        log.info("request for creating investment from data source {}", investmentInputDto);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                investmentInputDto.getRecipientId());
        ResponseEntity<Investor> investorResponseEntity = investorController.getOne(
                investmentInputDto.getInvestorId());
        ArtistPage recipient = artistPageResponseEntity.getBody();
        Investment investment = new Investment(investorResponseEntity.getBody(),
                null, investmentInputDto.getMoneyAmount());
        Investment investmentFromDataBase = investmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found investment with id = " + id));
        log.info("Investment from data base: " + investmentFromDataBase);
        investmentFromDataBase.getRecipients().add(recipient);
        BeanUtils.copyProperties(investment, investmentFromDataBase,
                NullProperty.getNullPropertiesString(investment));
        return new ResponseEntity<>(investmentRepo.save(investmentFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление инвестиции")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Investment investment) {

        log.info("request for deleting investment with parameters {}", investment);

        investmentRepo.delete(investment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех инвестиций")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all investments");
        investmentRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
