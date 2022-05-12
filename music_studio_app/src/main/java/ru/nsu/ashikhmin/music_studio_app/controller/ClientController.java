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
import ru.nsu.ashikhmin.music_studio_app.entity.Client;
import ru.nsu.ashikhmin.music_studio_app.entity.User;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.postdatasource.ClientDataSource;
import ru.nsu.ashikhmin.music_studio_app.repository.ClientRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("users/clients")
@Api(description = "Контроллер клиентов")
public class ClientController {
    
    private final ClientRepo clientRepo;

    private final UserController userController;

    @Autowired
    public ClientController(ClientRepo clientRepo, UserController userController){
        this.clientRepo = clientRepo;
        this.userController = userController;
    }

    @GetMapping
    @ApiOperation("Получение списка клиентов")
    public ResponseEntity<List<Client>> list(){
        log.info("request for getting all clients");
        List<Client> clients = clientRepo.findAll();
        if(clients.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение клиента по id")
    public ResponseEntity<Client> getOne(@PathVariable("id") Long id) {
        log.info("request for getting client with id: {}", id);

        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found client with id = " + id));

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового клиента")
    public ResponseEntity<Client> create(@Valid @RequestBody ClientDataSource clientDataSource){
        log.info("request for creating client from data source {}", clientDataSource);
        ResponseEntity<User> userResponseEntity = userController.getOne(clientDataSource.getUserId());
        Client client = new Client(userResponseEntity.getBody(),
                clientDataSource.getType());

        log.info("request for creating client with parameters {}", client);

        return new ResponseEntity<>(clientRepo.save(client), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем клиенте")
    public ResponseEntity<Client> update(@PathVariable("id") long id,
                                           @Valid @RequestBody ClientDataSource clientDataSource){

        log.info("request for updating client by id {} with parameters {}",
                id, clientDataSource);
        ResponseEntity<User> userResponseEntity = userController.getOne(clientDataSource.getUserId());
        Client client = new Client(userResponseEntity.getBody(),
                clientDataSource.getType());

        Client clientFromDataBase = clientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found client with id = " + id));
        log.info("Client from data base: " + clientFromDataBase);

        BeanUtils.copyProperties(client, clientFromDataBase,
                NullProperty.getNullPropertiesString(client));
        return new ResponseEntity<>(clientRepo.save(clientFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление клиента")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Client client) {

        log.info("request for deleting client with parameters {}", client);

        clientRepo.delete(client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех клиентов")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all clients");
        clientRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
