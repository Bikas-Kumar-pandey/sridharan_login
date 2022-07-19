package com.logindemo.login.controller;

import com.logindemo.login.entity.Address;
import com.logindemo.login.entity.LoginRequest;
import com.logindemo.login.entity.UserRequest;
import com.logindemo.login.entity.UserResponse;
import com.logindemo.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;


    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest request){
        return service.registerUser(request);
    }

    @GetMapping("/user/{id}")
    public UserResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @GetMapping("/allUsers")
    public List<UserResponse> getAllUsers(){
        return service.getAllUsers();
    }

    @PostMapping("/addAddress/{userId}")
    public String addAddress(@RequestBody List<Address> address, @PathVariable int userId){
          return  service.addAddress(address,userId);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return service.validateLogin(request);
    }


}
