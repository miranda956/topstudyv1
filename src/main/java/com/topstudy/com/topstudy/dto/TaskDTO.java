package com.topstudy.com.topstudy.dto;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Setter
@Getter


public class TaskDTO {
    private String taskId;
    private String name;
    private String description;
    private String category;
    private String documentUrl;
    private MultipartFile file;
    private String duration;
    private String wordCount;
    private String budget;
    private String remainingTime;
    private String paymentLink;
    private Boolean isPaid;
    // Include remaining time in DTO


}
