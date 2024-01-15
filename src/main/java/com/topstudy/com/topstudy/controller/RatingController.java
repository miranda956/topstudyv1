package com.topstudy.com.topstudy.controller;

import com.topstudy.com.topstudy.dto.RatingDTO;
import com.topstudy.com.topstudy.model.Rating;
import com.topstudy.com.topstudy.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solutions")
public class RatingController {

    @Autowired
    private RatingService ratingService; // You'll need to create this service

    // Rate a solution or service provider
    @PostMapping("/{solution_id}/ratings")
    public ResponseEntity<Rating> rateSolution(
            @PathVariable("solution_id") Long solutionId,
            @RequestBody RatingDTO ratingDTO) {
        Rating ratedSolution = ratingService.rateSolution(solutionId, ratingDTO);
        return new ResponseEntity<>(ratedSolution, HttpStatus.CREATED);
    }
}
