package com.topstudy.com.topstudy.dto;

import lombok.Data;

@Data
public class userRequest {
    private  String staffId;
    private String userName;
    private String password;
    private Boolean status;

}
