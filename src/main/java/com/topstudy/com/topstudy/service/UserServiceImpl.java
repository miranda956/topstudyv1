package com.topstudy.com.topstudy.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.topstudy.com.topstudy.model.User;
import com.topstudy.com.topstudy.repository.userRepository;
import com.topstudy.com.topstudy.exception.UserNotFoundException;

@Service
public class UserServiceImpl  implements UserServiceInterface  {


    private userRepository userRepository;

    @Autowired
    public UserServiceImpl(userRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException {
        User user = userRepository.findByUserNameAndPassword(name, password);
        if(user == null){
            throw new UserNotFoundException("Invalid id and password");
        }
        return user;
    }


}
