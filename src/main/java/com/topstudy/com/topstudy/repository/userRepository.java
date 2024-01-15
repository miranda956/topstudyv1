package com.topstudy.com.topstudy.repository;

import com.topstudy.com.topstudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface userRepository extends JpaRepository<User, Long> {
    Optional<User> findByStaffId(String staffId);

    public User findByUserNameAndPassword(String userName, String password);

}
