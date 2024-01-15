package com.topstudy.com.topstudy.config;
import java.util.Map;
import com.topstudy.com.topstudy.model.User;

public interface JwtGeneratorInterface {
    Map<String, String> generateToken(User user);

}
