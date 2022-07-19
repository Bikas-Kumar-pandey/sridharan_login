package com.logindemo.login.service;

import com.logindemo.login.entity.*;
import com.logindemo.login.exception.LoginUnSuccess;
import com.logindemo.login.exception.UserNotFound;
import com.logindemo.login.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserResponse registerUser(UserRequest request){
        User user=new User();
        String name= request.getFirstName()+" "+request.getLastName();
        user.setName(name);
        if(userRepo.existsByMobile(request.getMobile())){
            throw new RuntimeException("Mobile no: already exists");
        }
        user.setMobile(request.getMobile());
        if(userRepo.existsByMail(request.getMail())){
            throw new RuntimeException("Mail id already exists");
        }
        user.setMail(request.getMail());
        user.setPassword(request.getPassword());

        User savedUser = userRepo.save(user);
        UserResponse userResponse=new UserResponse();
        userResponse.setName(savedUser.getName());
        userResponse.setMobile(savedUser.getMobile());
        userResponse.setMail(savedUser.getMail());

        return userResponse;
    }

    public UserResponse getById(int id){
        Optional<User> byId = userRepo.findById(id);
        if(byId.isEmpty()){
            throw new UserNotFound("User not present with id : "+id);
        }
        User user = byId.get();
        UserResponse response=new UserResponse();
        response.setName(user.getName());
        response.setMobile(user.getMobile());
        response.setMail(user.getMail());
        return response;
    }
    public List<UserResponse> getAllUsers(){
        List<User> allUsers = userRepo.findAll();
        List<UserResponse> userResponses=new ArrayList<>();
        for(User user:allUsers){
            UserResponse response=new UserResponse();
            response.setName(user.getName());
            response.setMobile(user.getMobile());
            response.setMail(user.getMail());
            userResponses.add(response);
        }
        return userResponses;
    }

    public String addAddress(List<Address> address,int id){
        Optional<User> byId = userRepo.findById(id);
        if(byId.isEmpty()){
            throw new UserNotFound("User not present with id : "+id);
        }
        User user = byId.get();
        List<Address> addresses=null;
        if(user.getAddresses()!=null){
             addresses = user.getAddresses();
        }else {
            addresses=new ArrayList<>();
        }

        addresses.addAll(address);

        user.setAddresses(addresses);
        userRepo.save(user);
        return "Address saved";
    }

    public String validateLogin(LoginRequest request){
        String password = request.getPassword();
        String username = request.getUsername();
        if(userRepo.existsByMail(username) || userRepo.existsByMobile(username)){
            User user=null;
            if(userRepo.existsByMail(username)){
                user=userRepo.findByMail(username);
            }
            else {
                user=userRepo.findByMobile(username);
            }
            String password1 = user.getPassword();
            if(password.equals(password1)) {
               return "login success ";
            }
            else throw new LoginUnSuccess("Invalid username/password");
        }else {
            throw new LoginUnSuccess("Invalid username/password");
        }
    }
}
