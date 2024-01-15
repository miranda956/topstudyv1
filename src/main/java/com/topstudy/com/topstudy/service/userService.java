package com.topstudy.com.topstudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.topstudy.com.topstudy.dto.userRequest;
import com.topstudy.com.topstudy.model.User;
import com.topstudy.com.topstudy.repository.userRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import java.security.SecureRandom;





@Service
public class userService {
     // create new staff
    // activate user
    // deactivate user
@Autowired
private  userRepository userRepo;
private static final String ALLOWED_CHARACTERS = "0123456789";
private static final SecureRandom random = new SecureRandom();

    private static String generateStaffId(int length) {
        StringBuilder staffId = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            staffId.append(randomChar);
        }
        return staffId.toString();
    }
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public  userRequest createNewStaff (User staff){
    staff.setStaffId(generateStaffId(6));
    staff.setStatus(false);
    staff.setUserName(staff.getUserName());
    String hashedPassword = passwordEncoder.encode(staff.getPassword());
    staff.setPassword(hashedPassword);

    User response = userRepo.save(staff);
    userRequest staffRequest = new userRequest();
    staffRequest.setUserName(response.getUserName());
    staffRequest.setStaffId(response.getStaffId());
    staffRequest.setStatus(response.getStatus());
    staffRequest.setPassword(response.getPassword());
    return  staffRequest;




}


    public void activateUser(String staffId) {
        Optional<User> userOptional = userRepo.findByStaffId(staffId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus(true);
            userRepo.save(user);

        } else {
            // return status code and status message instead of throwing exception

            throw new IllegalArgumentException("User not found with staffId: " + staffId);
        }
    }

    public void deactivateUser(String staffId) {
        Optional<User> userOptional = userRepo.findByStaffId(staffId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus(false);
            userRepo.save(user);
        } else {
            // return status code and status message instead of throwing exception
            throw new IllegalArgumentException("User not found with staffId: " + staffId);
        }
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }




}
