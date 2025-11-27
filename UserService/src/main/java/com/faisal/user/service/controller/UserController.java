package com.faisal.user.service.controller;

import com.faisal.user.service.entities.User;
import com.faisal.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create user POST http://localhost:8081/users
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
//        User  u = userService.saveUser(user);
//        return new ResponseEntity<>(u, HttpStatus.CREATED);
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }
    //get all user  http://localhost:8081/users
    @GetMapping
    public ResponseEntity<List<User>> users(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAllUser());
    }

    // get single user http://localhots:8081/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> user(@PathVariable String id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getSingleUser(id));
    }

    //delete user  DELETE http://localhots:8081/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.deleteUser(id));
    }

    //update user PUT http://localhots:8081/users/{id}
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id,user));
    }


    //update user PUT http://localhots:8081/users/{id}
    @PatchMapping("{id}")
    public ResponseEntity<User> partialUpdateUser(@PathVariable String id, @RequestBody Map<String,Object> field){
        return ResponseEntity.status(HttpStatus.OK).body(userService.partialUpdateUser(id,field));
    }
}
