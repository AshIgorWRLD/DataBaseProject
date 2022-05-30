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
import ru.nsu.ashikhmin.music_studio_app.entity.*;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.dto.ArtistDistributionCardInputDto;
import ru.nsu.ashikhmin.music_studio_app.repository.ArtistDistributionCardRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("distribution/cards")
@Api(description = "Контроллер страниц дистрибуции")
public class ArtistDistributionCardController {

    private final ArtistDistributionCardRepo artistDistributionCardRepo;

    private final ArtistPageController artistPageController;
    private final DistributionServiceController distributionServiceController;

    @Autowired
    public ArtistDistributionCardController(ArtistDistributionCardRepo artistDistributionCardRepo,
                                ArtistPageController artistPageController,
                                DistributionServiceController distributionServiceController){
        this.artistDistributionCardRepo = artistDistributionCardRepo;
        this.artistPageController = artistPageController;
        this.distributionServiceController = distributionServiceController;
    }

    @GetMapping
    @ApiOperation("Получение списка страниц исполнителей")
    public ResponseEntity<Page<ArtistDistributionCard>> list(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        log.info("request for getting all artistDistributionCards");
        Page<ArtistDistributionCard> artistDistributionCards = artistDistributionCardRepo.findAll(pageable);
        if(artistDistributionCards.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(artistDistributionCards, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение страницы исполнителя по id")
    public ResponseEntity<ArtistDistributionCard> getOne(@PathVariable("id") Long id) {
        log.info("request for getting artistDistributionCard with id: {}", id);

        ArtistDistributionCard artistDistributionCard = artistDistributionCardRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found artistDistributionCard with id = " + id));

        return new ResponseEntity<>(artistDistributionCard, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание новой страницы исполнителя")
    public ResponseEntity<ArtistDistributionCard> create(@Valid @RequestBody ArtistDistributionCardInputDto artistDistributionCardInputDto){
        log.info("request for creating artistDistributionCard from data source {}", artistDistributionCardInputDto);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                artistDistributionCardInputDto.getArtistPageId());
        ResponseEntity<DistributionService> distributionServiceResponseEntity =
                distributionServiceController.getOne(artistDistributionCardInputDto.getDistributionServiceId());
        ArtistDistributionCard artistDistributionCard = new ArtistDistributionCard(artistPageResponseEntity.getBody(),
                distributionServiceResponseEntity.getBody(), artistDistributionCardInputDto.getListenWatchAmount(),
                artistDistributionCardInputDto.getMonthlyListeners());

        log.info("request for creating artistDistributionCard with parameters {}", artistDistributionCard);

        return new ResponseEntity<>(artistDistributionCardRepo.save(artistDistributionCard), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующей странице исполнителя")
    public ResponseEntity<ArtistDistributionCard> update(@PathVariable("id") long id,
                                             @Valid @RequestBody ArtistDistributionCardInputDto artistDistributionCardInputDto){

        log.info("request for updating artistDistributionCard by id {} with parameters {}",
                id, artistDistributionCardInputDto);
        ResponseEntity<ArtistPage> artistPageResponseEntity = artistPageController.getOne(
                artistDistributionCardInputDto.getArtistPageId());
        ResponseEntity<DistributionService> distributionServiceResponseEntity =
                distributionServiceController.getOne(artistDistributionCardInputDto.getDistributionServiceId());
        ArtistDistributionCard artistDistributionCard = new ArtistDistributionCard(artistPageResponseEntity.getBody(),
                distributionServiceResponseEntity.getBody(), artistDistributionCardInputDto.getListenWatchAmount(),
                artistDistributionCardInputDto.getMonthlyListeners());
        ArtistDistributionCard artistDistributionCardFromDataBase = artistDistributionCardRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found artistDistributionCard with id = " + id));
        log.info("ArtistDistributionCard from data base: " + artistDistributionCardFromDataBase);
        BeanUtils.copyProperties(artistDistributionCard, artistDistributionCardFromDataBase,
                NullProperty.getNullPropertiesString(artistDistributionCard));
        return new ResponseEntity<>(artistDistributionCardRepo.save(artistDistributionCardFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление страницы исполнителя")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") ArtistDistributionCard artistDistributionCard) {

        log.info("request for deleting artistDistributionCard with parameters {}", artistDistributionCard);

        artistDistributionCardRepo.delete(artistDistributionCard);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех страниц исполнителей")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all artistDistributionCards");
        artistDistributionCardRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
