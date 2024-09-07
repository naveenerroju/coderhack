package com.naveen.coderhack.controller;

import com.naveen.coderhack.model.RegisterUser;
import com.naveen.coderhack.model.User;
import com.naveen.coderhack.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService service;
    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers(){
        List<User> response = service.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@Valid @PathVariable(name = "userId") String userId){
        User response =  service.getUser(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<User> registerUser(@Valid @RequestBody RegisterUser user){
        User response = service.userRegistration(user.getUserId(), user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateScore(@PathVariable(name = "userId") String userId, @RequestBody int score){
        User response = service.updateScoreOfUser(userId, score);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "userId") String userId){
        service.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
