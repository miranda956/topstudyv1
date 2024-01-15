package com.topstudy.com.topstudy.controller;
import com.topstudy.com.topstudy.exception.UserNotFoundException;
import com.topstudy.com.topstudy.config.JwtGeneratorInterface;
import com.topstudy.com.topstudy.service.UserServiceInterface;
import org.springframework.http.HttpStatus;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.topstudy.com.topstudy.service.userService;
import com.topstudy.com.topstudy.dto.userRequest;
import com.topstudy.com.topstudy.model.User;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class staffController {

    private JwtGeneratorInterface jwtGenerator;
    private UserServiceInterface userServiceInterface;

    @Autowired
    public staffController(UserServiceInterface userServiceInterface, JwtGeneratorInterface jwtGenerator){
        this.userServiceInterface=userServiceInterface;
        this.jwtGenerator=jwtGenerator;
    }


    @Autowired
    private  userService userInstance;

    @PostMapping("/newstaff")
    public userRequest createStaff(@RequestBody User staff){

        return userInstance.createNewStaff(staff);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            if(user.getUserName() == null || user.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            User userData = userServiceInterface.getUserByNameAndPassword(user.getUserName(), user.getPassword());
            if(userData == null){
                throw new UserNotFoundException("UserName or Password is Invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/staff")
    public List<User> getAllUsers() {
        return userInstance.getAllUsers();
    }

    @PutMapping("/activate/{staffId}")
    public void activateStaff(@PathVariable String staffId) {
        userInstance.activateUser(staffId);
    }

    @PutMapping("/deactivate/{staffId}")
    public void deactivateStaff(@PathVariable String staffId) {
        userInstance.deactivateUser(staffId);
    }



}
