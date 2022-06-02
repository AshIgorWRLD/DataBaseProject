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
import ru.nsu.ashikhmin.music_studio_app.entity.ArtistBill;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.repository.ArtistBillRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("bills/artist")
@Api(description = "Контроллер счетов исполнителей")
public class ArtistBillController {

    private final ArtistBillRepo artistBillRepo;

    @Autowired
    public ArtistBillController(ArtistBillRepo artistBillRepo) {
        this.artistBillRepo = artistBillRepo;
    }

    @GetMapping
    @ApiOperation("Получение списка счетов исполнителей")
    public ResponseEntity<Page<ArtistBill>> list(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        log.info("request for getting all artistBills");
        Page<ArtistBill> artistBills = artistBillRepo.findAll(pageable);
        if(artistBills.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(artistBills, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение счетов исполнителей по id")
    public ResponseEntity<ArtistBill> getOne(@PathVariable("id") Long id) {
        log.info("request for getting artistBill with id: {}", id);

        ArtistBill person = artistBillRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found artistBill with id = " + id));

        log.info("{}",person);

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового счета исполнителя")
    public ResponseEntity<ArtistBill> create(@Valid @RequestBody ArtistBill artistBill){
        log.info("request for creating person with parameters {}", artistBill);

        return new ResponseEntity<>(artistBillRepo.save(artistBill), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем счете исполнителя")
    public ResponseEntity<ArtistBill> update(@PathVariable("id") long id,
                                               @Valid @RequestBody ArtistBill artistBill){
        log.info("request for updating artistBill by id {} with parameters {}",
                id, artistBill);

        ArtistBill personFromDataBase = artistBillRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found artistBill with id = " + id));
        log.info("ArtistBill from data base: " + personFromDataBase);

        BeanUtils.copyProperties(artistBill, personFromDataBase,
                NullProperty.getNullPropertiesString(artistBill));
        return new ResponseEntity<>(artistBillRepo.save(personFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление счета исполнителя")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") ArtistBill artistBill) {

        log.info("request for deleting artistBill with parameters {}", artistBill);

        artistBillRepo.delete(artistBill);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех счетов исполнителей")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all artistBills");
        artistBillRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
