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
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistPage;
import ru.nsu.ashikhmin.music_studio_app.entity.ConcertAndContractIncome;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.postdatasource.ConcertAndContractIncomeDataSource;
import ru.nsu.ashikhmin.music_studio_app.repository.ConcertAndContractIncomeRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/con_incomes")
@Api(description = "Контроллер доходов с концертов и контрактов")
public class ConcertAndContractIncomeController {

    private final ConcertAndContractIncomeRepo concertAndContractIncomeRepo;

    private final ArtistPageController artistPageController;

    @Autowired
    public ConcertAndContractIncomeController(ConcertAndContractIncomeRepo concertAndContractIncomeRepo,
                                              ArtistPageController artistPageController){
        this.concertAndContractIncomeRepo = concertAndContractIncomeRepo;
        this.artistPageController = artistPageController;
    }

    @GetMapping
    @ApiOperation("Получение списка доходов с концертов и контрактов")
    public ResponseEntity<List<ConcertAndContractIncome>> list(){
        log.info("request for getting all concertAndContractIncomes");
        List<ConcertAndContractIncome> concertAndContractIncomes = concertAndContractIncomeRepo.findAll();
        if(concertAndContractIncomes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(concertAndContractIncomes, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение дохода с концерта или контракта по id")
    public ResponseEntity<ConcertAndContractIncome> getOne(@PathVariable("id") Long id) {
        log.info("request for getting concertAndContractIncome with id: {}", id);

        ConcertAndContractIncome concertAndContractIncome = concertAndContractIncomeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found concertAndContractIncome with id = " + id));

        return new ResponseEntity<>(concertAndContractIncome, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового дохода с концерта или контракта")
    public ResponseEntity<ConcertAndContractIncome> create(
            @Valid @RequestBody ConcertAndContractIncomeDataSource concertAndContractIncomeDataSource){
        log.info("request for creating concertAndContractIncome from data source {}", concertAndContractIncomeDataSource);
        ResponseEntity<ArtistPage> artistPageResponseEntity =
                artistPageController.getOne(
                concertAndContractIncomeDataSource.getArtistOrGroupId());
        ConcertAndContractIncome concertAndContractIncome = new ConcertAndContractIncome(
                artistPageResponseEntity.getBody(),
                concertAndContractIncomeDataSource.getType(),
                concertAndContractIncomeDataSource.getName(),
                concertAndContractIncomeDataSource.getMoneyAmount());
        log.info("request for creating concertAndContractIncome with parameters {}", concertAndContractIncome);
        return new ResponseEntity<>(concertAndContractIncomeRepo.save(concertAndContractIncome), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем доходе с концерта или контракта")
    public ResponseEntity<ConcertAndContractIncome> update(@PathVariable("id") long id,
                                         @Valid @RequestBody ConcertAndContractIncomeDataSource concertAndContractIncomeDataSource){

        log.info("request for updating concertAndContractIncome by id {} with parameters {}",
                id, concertAndContractIncomeDataSource);
        ResponseEntity<ArtistPage> artistPageResponseEntity =
                artistPageController.getOne(
                        concertAndContractIncomeDataSource.getArtistOrGroupId());
        ConcertAndContractIncome concertAndContractIncome = new ConcertAndContractIncome(
                artistPageResponseEntity.getBody(),
                concertAndContractIncomeDataSource.getType(),
                concertAndContractIncomeDataSource.getName(),
                concertAndContractIncomeDataSource.getMoneyAmount());
        ConcertAndContractIncome concertAndContractIncomeFromDataBase = concertAndContractIncomeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found concertAndContractIncome with id = " + id));
        log.info("ConcertAndContractIncome from data base: " + concertAndContractIncomeFromDataBase);
        BeanUtils.copyProperties(concertAndContractIncome, concertAndContractIncomeFromDataBase,
                NullProperty.getNullPropertiesString(concertAndContractIncome));
        return new ResponseEntity<>(concertAndContractIncomeRepo.save(concertAndContractIncomeFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление дохода с концерта или контракта")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") ConcertAndContractIncome concertAndContractIncome) {

        log.info("request for deleting concertAndContractIncome with parameters {}", concertAndContractIncome);

        concertAndContractIncomeRepo.delete(concertAndContractIncome);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех доходов с концертов или контрактов")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all concertAndContractIncomes");
        concertAndContractIncomeRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
