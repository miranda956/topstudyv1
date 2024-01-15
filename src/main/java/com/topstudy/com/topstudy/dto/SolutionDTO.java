package com.topstudy.com.topstudy.dto;
import org.springframework.web.multipart.MultipartFile;


import lombok.Data;

import java.util.Date;

@Data

public class SolutionDTO {

    private String description;
    private String documentUrl;
    private Enum status;
    private MultipartFile file;
    private Date submissionDate;



    // Constructors, getters, and setters
}
