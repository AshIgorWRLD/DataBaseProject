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
import ru.nsu.ashikhmin.music_studio_app.entity.User;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.postdatasource.InvestorDataSource;
import ru.nsu.ashikhmin.music_studio_app.repository.InvestorRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("users/investors")
@Api(description = "Контроллер инвесторов")
public class InvestorController {

    private final InvestorRepo investorRepo;

    private final UserController userController;

    @Autowired
    public InvestorController(InvestorRepo investorRepo, UserController userController){
        this.investorRepo = investorRepo;
        this.userController = userController;
    }

    @GetMapping
    @ApiOperation("Получение списка инвесторов")
    public ResponseEntity<List<Investor>> list(){
        log.info("request for getting all investors");
        List<Investor> investors = investorRepo.findAll();
        if(investors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(investors, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение инвестора по id")
    public ResponseEntity<Investor> getOne(@PathVariable("id") Long id) {
        log.info("request for getting investor with id: {}", id);

        Investor investor = investorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found investor with id = " + id));

        return new ResponseEntity<>(investor, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового инвестора")
    public ResponseEntity<Investor> create(@Valid @RequestBody InvestorDataSource investorDataSource){
        log.info("request for creating investor from data source {}", investorDataSource);
        ResponseEntity<User> userResponseEntity = userController.getOne(investorDataSource.getUserId());
        Investor investor = new Investor(userResponseEntity.getBody(),
                investorDataSource.getInvestedMoney(), investorDataSource.getBusinessPart());

        log.info("request for creating investor with parameters {}", investor);

        return new ResponseEntity<>(investorRepo.save(investor), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем инвесторе")
    public ResponseEntity<Investor> update(@PathVariable("id") long id,
                                           @Valid @RequestBody InvestorDataSource investorDataSource){

        log.info("request for updating investor by id {} with parameters {}",
                id, investorDataSource);
        ResponseEntity<User> userResponseEntity = userController.getOne(investorDataSource.getUserId());
        Investor investor = new Investor(userResponseEntity.getBody(),
                investorDataSource.getInvestedMoney(), investorDataSource.getBusinessPart());

        Investor investorFromDataBase = investorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found investor with id = " + id));
        log.info("Investor from data base: " + investorFromDataBase);

        BeanUtils.copyProperties(investor, investorFromDataBase,
                NullProperty.getNullPropertiesString(investor));
        return new ResponseEntity<>(investorRepo.save(investorFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление инвестора")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Investor investor) {

        log.info("request for deleting investor with parameters {}", investor);

        investorRepo.delete(investor);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех инвесторов")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all investors");
        investorRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
