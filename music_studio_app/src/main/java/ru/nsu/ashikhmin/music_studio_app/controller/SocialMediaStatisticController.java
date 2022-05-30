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
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistPage;
import ru.nsu.ashikhmin.music_studio_app.entity.SocialMediaStatistic;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.dto.SocialMediaStatisticInputDto;
import ru.nsu.ashikhmin.music_studio_app.repository.SocialMediaStatisticRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("sm/statistic")
@Api(description = "Контроллер статистики соц сетей")
public class SocialMediaStatisticController {

    private final SocialMediaStatisticRepo socialMediaStatisticRepo;

    private final ArtistPageController artistPageController;

    @Autowired
    public SocialMediaStatisticController(SocialMediaStatisticRepo socialMediaStatisticRepo,
                                          ArtistPageController artistPageController){
        this.socialMediaStatisticRepo = socialMediaStatisticRepo;
        this.artistPageController = artistPageController;
    }

    @GetMapping
    @ApiOperation("Получение списка инвесторов")
    public ResponseEntity<Page<SocialMediaStatistic>> list(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        log.info("request for getting all socialMediaStatistics");
        Page<SocialMediaStatistic> socialMediaStatistics = socialMediaStatisticRepo.findAll(pageable);
        if(socialMediaStatistics.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(socialMediaStatistics, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение инвестора по id")
    public ResponseEntity<SocialMediaStatistic> getOne(@PathVariable("id") Long id) {
        log.info("request for getting socialMediaStatistic with id: {}", id);

        SocialMediaStatistic socialMediaStatistic = socialMediaStatisticRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found socialMediaStatistic with id = " + id));

        return new ResponseEntity<>(socialMediaStatistic, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового инвестора")
    public ResponseEntity<SocialMediaStatistic> create(@Valid @RequestBody SocialMediaStatisticInputDto socialMediaStatisticInputDto){
        log.info("request for creating socialMediaStatistic from data source {}",
                socialMediaStatisticInputDto);
        ResponseEntity<ArtistPage> artistPageControllerResponseEntity =
                artistPageController.getOne(socialMediaStatisticInputDto.getArtistPageId());
        SocialMediaStatistic socialMediaStatistic = new SocialMediaStatistic(
                artistPageControllerResponseEntity.getBody(),
                socialMediaStatisticInputDto.getSocialNetwork(),
                socialMediaStatisticInputDto.getSubscribersAmount(),
                socialMediaStatisticInputDto.getLiveSubscribers());

        log.info("request for creating socialMediaStatistic with parameters {}", socialMediaStatistic);

        return new ResponseEntity<>(socialMediaStatisticRepo.save(socialMediaStatistic), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем инвесторе")
    public ResponseEntity<SocialMediaStatistic> update(@PathVariable("id") long id,
                                           @Valid @RequestBody SocialMediaStatisticInputDto socialMediaStatisticInputDto){

        log.info("request for updating socialMediaStatistic by id {} with parameters {}",
                id, socialMediaStatisticInputDto);
        ResponseEntity<ArtistPage> artistPageControllerResponseEntity =
                artistPageController.getOne(socialMediaStatisticInputDto.getArtistPageId());
        SocialMediaStatistic socialMediaStatistic = new SocialMediaStatistic(
                artistPageControllerResponseEntity.getBody(),
                socialMediaStatisticInputDto.getSocialNetwork(),
                socialMediaStatisticInputDto.getSubscribersAmount(),
                socialMediaStatisticInputDto.getLiveSubscribers());
        SocialMediaStatistic socialMediaStatisticFromDataBase = socialMediaStatisticRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found socialMediaStatistic with id = " + id));
        log.info("SocialMediaStatistic from data base: " + socialMediaStatisticFromDataBase);

        BeanUtils.copyProperties(socialMediaStatistic, socialMediaStatisticFromDataBase,
                NullProperty.getNullPropertiesString(socialMediaStatistic));
        return new ResponseEntity<>(socialMediaStatisticRepo.save(socialMediaStatisticFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление инвестора")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") SocialMediaStatistic socialMediaStatistic) {

        log.info("request for deleting socialMediaStatistic with parameters {}", socialMediaStatistic);

        socialMediaStatisticRepo.delete(socialMediaStatistic);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех инвесторов")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all socialMediaStatistics");
        socialMediaStatisticRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
