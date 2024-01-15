package com.topstudy.com.topstudy.service;

import com.topstudy.com.topstudy.dto.RatingDTO;
import com.topstudy.com.topstudy.model.Rating;
import com.topstudy.com.topstudy.model.Solution;
import com.topstudy.com.topstudy.repository.RatingRepository;
import com.topstudy.com.topstudy.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private SolutionRepository solutionRepository;

    @Transactional
    public Rating rateSolution(Long solutionId, RatingDTO ratingDTO) {
        Solution solution = solutionRepository.findById(solutionId).orElse(null);

        if (solution != null) {
            Rating rating = new Rating();
            rating.setSolution(solution);
            rating.setRatingValue(ratingDTO.getRatingValue());
            rating.setRatingDate(new Date());
            return ratingRepository.save(rating);
        }

        return null; // Return null if the solution with the specified ID is not found
    }
}
