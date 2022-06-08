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
import ru.nsu.ashikhmin.music_studio_app.dto.CustomDistributionServiceInputDto;
import ru.nsu.ashikhmin.music_studio_app.entity.DistributionService;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.repository.DistributionServiceRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;
import ru.nsu.ashikhmin.music_studio_app.utils.SQLAdds;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("distribution/services")
@Api(description = "Контроллер сервисов дистрибуции")
public class DistributionServiceController {

    private final DistributionServiceRepo distributionServiceRepo;
    private final EntityManager entityManager;

    @Autowired
    public DistributionServiceController(DistributionServiceRepo distributionServiceRepo, EntityManager entityManager) {
        this.distributionServiceRepo = distributionServiceRepo;
        this.entityManager = entityManager;
    }

    @GetMapping
    @ApiOperation("Получение списка сервисов дистрибуции")
    public ResponseEntity<Page<DistributionService>> list(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        log.info("request for getting all distributionServices");
        Page<DistributionService> distributionServices = distributionServiceRepo.findAll(pageable);
        if (distributionServices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(distributionServices, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение сервиса дистрибуции по id")
    public ResponseEntity<DistributionService> getOne(@PathVariable("id") Long id) {
        log.info("request for getting distributionService with id: {}", id);

        DistributionService person = distributionServiceRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found distributionService with id = " + id));

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("custom")
    @ApiOperation("Получение сервисов дистрибуции по заданным пармаетрам")
    public ResponseEntity<List<DistributionService>> getCustom(
            @Valid @RequestBody CustomDistributionServiceInputDto customDistributionServiceInputDto){
        log.info("request for filtering distribution services with values: {}", customDistributionServiceInputDto);

        StringBuilder sqlRequest = new StringBuilder();

        List<String> fields = new ArrayList<>();
        fields.add("*");

        List<String> fromTables = new ArrayList<>();
        fromTables.add(SQLAdds.DISTRIBUTION_SERVICE_TABLE);

        SQLAdds.addSelectLine(sqlRequest, fields);
        SQLAdds.addFromLine(sqlRequest, fromTables);
        if(customDistributionServiceInputDto.isNotEmpty()){
            Boolean[] isNotFirst = new Boolean[1];
            isNotFirst[0] = false;
            sqlRequest.append(SQLAdds.WHERE)
                    .append("(");
            customDistributionServiceInputDto.addType(sqlRequest, isNotFirst);
            SQLAdds.addBarParameter(sqlRequest, isNotFirst,
                    customDistributionServiceInputDto.isListenWatchCost(),
                    customDistributionServiceInputDto.isListenWatchCostAnd(),
                    customDistributionServiceInputDto.getLowestListenWatchCost(),
                    customDistributionServiceInputDto.getHighestListenWatchCost(),
                    "listen_watch_cost", SQLAdds.DISTRIBUTION_SERVICE_TABLE);
            sqlRequest.append(")");
        }
        if (customDistributionServiceInputDto.isFiltrationField()) {
            sqlRequest.append(" ")
                    .append(SQLAdds.ORDER_BY)
                    .append(" ")
                    .append(customDistributionServiceInputDto.getFiltration())
                    .append(";");
        }
        log.info("Filtration request: " + sqlRequest.toString());
        List<DistributionService> out = entityManager.createNativeQuery(sqlRequest.toString(),
                        DistributionService.class).getResultList();
        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового сервиса дистрибуции")
    public ResponseEntity<DistributionService> create(@Valid @RequestBody DistributionService distributionService) {
        log.info("request for creating person with parameters {}", distributionService);

        return new ResponseEntity<>(distributionServiceRepo.save(distributionService), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем сервисе дистрибуции")
    public ResponseEntity<DistributionService> update(@PathVariable("id") long id,
                                                      @Valid @RequestBody DistributionService distributionService) {
        log.info("request for updating distributionService by id {} with parameters {}",
                id, distributionService);

        DistributionService personFromDataBase = distributionServiceRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found distributionService with id = " + id));
        log.info("DistributionService from data base: " + personFromDataBase);

        BeanUtils.copyProperties(distributionService, personFromDataBase,
                NullProperty.getNullPropertiesString(distributionService));
        return new ResponseEntity<>(distributionServiceRepo.save(personFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление сервиса дистрибуции")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") DistributionService distributionService) {

        log.info("request for deleting distributionService with parameters {}", distributionService);

        distributionServiceRepo.delete(distributionService);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех сервисов дистрибуции")
    public ResponseEntity<HttpStatus> deleteAll() {
        log.info("request for deleting all distributionServices");
        distributionServiceRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
