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
import ru.nsu.ashikhmin.music_studio_app.entity.Progress;
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistPage;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.dto.ProgressInputDto;
import ru.nsu.ashikhmin.music_studio_app.repository.ProgressRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("artist_pages/progress")
@Api(description = "Контроллер прогресса")
public class ProgressController {
    private final ProgressRepo progressRepo;

    private final ArtistPageController artistPageController;

    @Autowired
    public ProgressController(ProgressRepo progressRepo,
                                          ArtistPageController artistPageController){
        this.progressRepo = progressRepo;
        this.artistPageController = artistPageController;
    }

    @GetMapping
    @ApiOperation("Получение списка прогрессов")
    public ResponseEntity<Page<Progress>> list(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        log.info("request for getting all progresss");
        Page<Progress> progresss = progressRepo.findAll(pageable);
        if(progresss.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(progresss, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение страницы прогресса id")
    public ResponseEntity<Progress> getOne(@PathVariable("id") Long id) {
        log.info("request for getting progress with id: {}", id);

        Progress progress = progressRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found progress with id = " + id));

        return new ResponseEntity<>(progress, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание новой страницы исполнителя")
    public ResponseEntity<Progress> create(@Valid @RequestBody ProgressInputDto progressInputDto){
        log.info("request for creating progress from data source {}",
                progressInputDto);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                progressInputDto.getArtistPageId());
        Progress progress = new Progress(
                artistPageResponseEntity.getBody(),
                progressInputDto.getSocialMediaCoefficient(),
                progressInputDto.getAdvertisementCompaniesCoefficient(),
                progressInputDto.getDistributionCoefficient(),
                progressInputDto.getIncomesCoefficient(),
                progressInputDto.getSupposedSuccessDate());

        log.info("request for creating progress with parameters {}", progress);

        return new ResponseEntity<>(progressRepo.save(progress), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующей странице исполнителя")
    public ResponseEntity<Progress> update(@PathVariable("id") long id,
                                                       @Valid @RequestBody ProgressInputDto progressInputDto){

        log.info("request for updating progress by id {} with parameters {}",
                id, progressInputDto);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                progressInputDto.getArtistPageId());
        Progress progress = new Progress(
                artistPageResponseEntity.getBody(),
                progressInputDto.getSocialMediaCoefficient(),
                progressInputDto.getAdvertisementCompaniesCoefficient(),
                progressInputDto.getDistributionCoefficient(),
                progressInputDto.getIncomesCoefficient(),
                progressInputDto.getSupposedSuccessDate());
        Progress progressFromDataBase = progressRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found progress with id = " + id));
        log.info("Progress from data base: " + progressFromDataBase);
        BeanUtils.copyProperties(progress, progressFromDataBase,
                NullProperty.getNullPropertiesString(progress));
        return new ResponseEntity<>(progressRepo.save(progressFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление страницы исполнителя")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Progress progress) {

        log.info("request for deleting progress with parameters {}", progress);

        progressRepo.delete(progress);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех страниц исполнителей")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all progresss");
        progressRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
