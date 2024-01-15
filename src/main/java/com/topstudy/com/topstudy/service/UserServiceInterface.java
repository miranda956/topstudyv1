package com.topstudy.com.topstudy.service;

import org.springframework.stereotype.Service;
import com.topstudy.com.topstudy.model.User;
import com.topstudy.com.topstudy.exception.UserNotFoundException;

@Service
public interface UserServiceInterface {

    public void saveUser(User user);
    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException;


}
