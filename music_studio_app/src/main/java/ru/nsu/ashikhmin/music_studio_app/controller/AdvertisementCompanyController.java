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
import ru.nsu.ashikhmin.music_studio_app.entity.AdvertisementCompany;
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistPage;
import ru.nsu.ashikhmin.music_studio_app.entity.DistributionService;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.postdatasource.AdvertisementCompanyDataSource;
import ru.nsu.ashikhmin.music_studio_app.repository.AdvertisementCompanyRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Validated
@RestController
@RequestMapping("advertisement/companies")
@Api(description = "Контроллер рекламных компаний")
public class AdvertisementCompanyController {

    private final AdvertisementCompanyRepo advertisementCompanyRepo;

    private final ArtistPageController artistPageController;

    @Autowired
    public AdvertisementCompanyController(AdvertisementCompanyRepo advertisementCompanyRepo,
                                            ArtistPageController artistPageController){
        this.advertisementCompanyRepo = advertisementCompanyRepo;
        this.artistPageController = artistPageController;
    }

    @GetMapping
    @ApiOperation("Получение списка страниц исполнителей")
    public ResponseEntity<List<AdvertisementCompany>> list(){
        log.info("request for getting all advertisementCompanys");
        List<AdvertisementCompany> advertisementCompanys = advertisementCompanyRepo.findAll();
        if(advertisementCompanys.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(advertisementCompanys, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение страницы исполнителя по id")
    public ResponseEntity<AdvertisementCompany> getOne(@PathVariable("id") Long id) {
        log.info("request for getting advertisementCompany with id: {}", id);

        AdvertisementCompany advertisementCompany = advertisementCompanyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found advertisementCompany with id = " + id));

        return new ResponseEntity<>(advertisementCompany, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание новой страницы исполнителя")
    public ResponseEntity<AdvertisementCompany> create(@Valid @RequestBody AdvertisementCompanyDataSource advertisementCompanyDataSource){
        log.info("request for creating advertisementCompany from data source {}",
                advertisementCompanyDataSource);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                advertisementCompanyDataSource.getArtistPageId());
        AdvertisementCompany advertisementCompany = new AdvertisementCompany(
                artistPageResponseEntity.getBody(),
                advertisementCompanyDataSource.getAdvertisementCompanyName(),
                advertisementCompanyDataSource.getStartDate(),
                advertisementCompanyDataSource.getEndDate(),
                advertisementCompanyDataSource.getAuditoryType(),
                advertisementCompanyDataSource.getAdvertisementType(),
                advertisementCompanyDataSource.getExpenses());

        log.info("request for creating advertisementCompany with parameters {}", advertisementCompany);

        return new ResponseEntity<>(advertisementCompanyRepo.save(advertisementCompany), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующей странице исполнителя")
    public ResponseEntity<AdvertisementCompany> update(@PathVariable("id") long id,
                                                         @Valid @RequestBody AdvertisementCompanyDataSource advertisementCompanyDataSource){

        log.info("request for updating advertisementCompany by id {} with parameters {}",
                id, advertisementCompanyDataSource);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                advertisementCompanyDataSource.getArtistPageId());
        AdvertisementCompany advertisementCompany = new AdvertisementCompany(
                artistPageResponseEntity.getBody(),
                advertisementCompanyDataSource.getAdvertisementCompanyName(),
                advertisementCompanyDataSource.getStartDate(),
                advertisementCompanyDataSource.getEndDate(),
                advertisementCompanyDataSource.getAuditoryType(),
                advertisementCompanyDataSource.getAdvertisementType(),
                advertisementCompanyDataSource.getExpenses());
        AdvertisementCompany advertisementCompanyFromDataBase = advertisementCompanyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found advertisementCompany with id = " + id));
        log.info("AdvertisementCompany from data base: " + advertisementCompanyFromDataBase);
        BeanUtils.copyProperties(advertisementCompany, advertisementCompanyFromDataBase,
                NullProperty.getNullPropertiesString(advertisementCompany));
        return new ResponseEntity<>(advertisementCompanyRepo.save(advertisementCompanyFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление страницы исполнителя")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") AdvertisementCompany advertisementCompany) {

        log.info("request for deleting advertisementCompany with parameters {}", advertisementCompany);

        advertisementCompanyRepo.delete(advertisementCompany);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех страниц исполнителей")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all advertisementCompanys");
        advertisementCompanyRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
