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
import ru.nsu.ashikhmin.music_studio_app.entity.User;
import ru.nsu.ashikhmin.music_studio_app.exceptions.ResourceNotFoundException;
import ru.nsu.ashikhmin.music_studio_app.repository.UserRepo;
import ru.nsu.ashikhmin.music_studio_app.utils.NullProperty;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("users")
@Api(description = "Контроллер пользователей")
public class UserController{

    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @GetMapping
    @ApiOperation("Получение списка пользователей")
    public ResponseEntity<Page<User>> list(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable){
        log.info("request for getting all users");
        Page<User> users = userRepo.findAll(pageable);
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение пользователя по id")
    public ResponseEntity<User> getOne(@PathVariable("id") Long id) {
        log.info("request for getting user with id: {}", id);

        User person = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found user with id = " + id));

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation("Создание нового пользователя")
    public ResponseEntity<User> create(@Valid @RequestBody User user){
        log.info("request for creating person with parameters {}", user);

        return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation("Обновление информации о существующем пользователе")
    public ResponseEntity<User> update(@PathVariable("id") long id,
                                         @Valid @RequestBody User user){
        log.info("request for updating user by id {} with parameters {}",
                id, user);

        User personFromDataBase = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found user with id = " + id));
        log.info("User from data base: " + personFromDataBase);

        BeanUtils.copyProperties(user, personFromDataBase,
                NullProperty.getNullPropertiesString(user));
        return new ResponseEntity<>(userRepo.save(personFromDataBase), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление пользователя")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") User user) {

        log.info("request for deleting user with parameters {}", user);

        userRepo.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("Удаление всех пользователей")
    public ResponseEntity<HttpStatus> deleteAll(){
        log.info("request for deleting all users");
        userRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
