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
import ru.nsu.ashikhmin.music_studio_app.entity.Artist;
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistBill;
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistPayment;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.dto.ArtistPaymentInputDto;
import ru.nsu.ashikhmin.music_studio_app.repository.ArtistPaymentRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Validated
@RestController
@RequestMapping("payments/artist")
@Api(description = "Контроллер выплат исполнителям")
public class ArtistPaymentController {

    private final ArtistPaymentRepo artistPaymentRepo;

    private final ArtistController artistController;
    private final ArtistBillController artistBillController;

    @Autowired
    public ArtistPaymentController(ArtistPaymentRepo artistPaymentRepo,
                                     ArtistController artistController,
                                     ArtistBillController artistBillController){
        this.artistPaymentRepo = artistPaymentRepo;
        this.artistController = artistController;
        this.artistBillController = artistBillController;
    }

    @GetMapping
    @ApiOperation("Получение списка выплат исполнителям")
    public ResponseEntity<List<ArtistPayment>> list(){
        log.info("request for getting all artistPayments");
        List<ArtistPayment> artistPayments = artistPaymentRepo.findAll();
        if(artistPayments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(artistPayments, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение выплаты исполнителю по id")
    public ResponseEntity<ArtistPayment> getOne(@PathVariable("id") Long id) {
        log.info("request for getting artistPayment with id: {}", id);

        ArtistPayment artistPayment = artistPaymentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found artistPayment with id = " + id));

        return new ResponseEntity<>(artistPayment, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание новой выплаты исполнителю")
    public ResponseEntity<ArtistPayment> create(@Valid @RequestBody ArtistPaymentInputDto artistPaymentInputDto){
        log.info("request for creating artistPayment from data source {}", artistPaymentInputDto);
        ResponseEntity<Artist> artistResponseEntity = artistController.getOne(
                artistPaymentInputDto.getArtistId());
        ResponseEntity<ArtistBill> artistBillResponseEntity = artistBillController.getOne(
                artistPaymentInputDto.getArtistBillId());
        ArtistBill artistBill = artistBillResponseEntity.getBody();
        log.info("artist bill {}", artistBill);
        Set<ArtistBill> set = new HashSet<>();
        set.add(artistBill);
        set.forEach(x->log.info("set {}", x));
        ArtistPayment artistPayment = new ArtistPayment(artistResponseEntity.getBody(),
                set, artistPaymentInputDto.getAmount());
        artistPayment.setArtistBillId(artistBill.getId());
        log.info("request for creating artistPayment with parameters {}", artistPayment);
        return new ResponseEntity<>(artistPaymentRepo.save(artistPayment), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующей выплате исполнителю")
    public ResponseEntity<ArtistPayment> update(@PathVariable("id") long id,
                                                  @Valid @RequestBody ArtistPaymentInputDto artistPaymentInputDto){

        log.info("request for updating artistPayment by id {} with parameters {}",
                id, artistPaymentInputDto);
        ResponseEntity<Artist> artistResponseEntity = artistController.getOne(
                artistPaymentInputDto.getArtistId());
        ResponseEntity<ArtistBill> artistBillResponseEntity = artistBillController.getOne(
                artistPaymentInputDto.getArtistBillId());
        ArtistBill artistBill = artistBillResponseEntity.getBody();
        ArtistPayment artistPayment = new ArtistPayment(artistResponseEntity.getBody(),
                null, artistPaymentInputDto.getAmount());
        ArtistPayment artistPaymentFromDataBase = artistPaymentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found artistPayment with id = " + id));
        log.info("ArtistPayment from data base: " + artistPaymentFromDataBase);
        artistPaymentFromDataBase.getArtistBills().add(artistBill);
        BeanUtils.copyProperties(artistPayment, artistPaymentFromDataBase,
                NullProperty.getNullPropertiesString(artistPayment));
        return new ResponseEntity<>(artistPaymentRepo.save(artistPaymentFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление выплаты исполнителю")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") ArtistPayment artistPayment) {

        log.info("request for deleting artistPayment with parameters {}", artistPayment);

        artistPaymentRepo.delete(artistPayment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех выплат исполнителям")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all artistPayments");
        artistPaymentRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
