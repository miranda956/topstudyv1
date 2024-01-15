package com.topstudy.com.topstudy.controller;

import com.topstudy.com.topstudy.dto.SolutionDTO;
import com.topstudy.com.topstudy.model.Solution;
import com.topstudy.com.topstudy.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/solutions")
public class SolutionController {

    @Autowired
    private SolutionService solutionService; // You'll need to create this service

    // Submit a solution for a task
    @PostMapping("/{task_id}/solutions")
    public ResponseEntity<Solution> submitSolution(
            @PathVariable("task_id") Long taskId,
            @RequestBody SolutionDTO solutionDTO) throws IOException {
        Solution submittedSolution = solutionService.submitSolution(taskId, solutionDTO);
        return new ResponseEntity<>(submittedSolution, HttpStatus.CREATED);
    }

    // Update solution details by ID
    @PutMapping("/{solution_id}")
    public ResponseEntity<Solution> updateSolution(
            @PathVariable("solution_id") Long solutionId,
            @RequestBody SolutionDTO solutionDTO) {
        Solution updatedSolution = solutionService.updateSolution(solutionId, solutionDTO);
        if (updatedSolution != null) {
            return new ResponseEntity<>(updatedSolution, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get solution details by ID
    @GetMapping("/{solution_id}")
    public ResponseEntity<Solution> getSolutionById(@PathVariable("solution_id") Long solutionId) {
        Solution solution = solutionService.getSolutionById(solutionId);
        if (solution != null) {
            return new ResponseEntity<>(solution, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Accept or reject a solution
    @PutMapping("/{solution_id}/accept")
    public ResponseEntity<Solution> acceptSolution(@PathVariable("solution_id") Long solutionId) {
        Solution acceptedSolution = solutionService.acceptSolution(solutionId);
        if (acceptedSolution != null) {
            return new ResponseEntity<>(acceptedSolution, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{solution_id}/reject")
    public ResponseEntity<Solution> rejectSolution(@PathVariable("solution_id") Long solutionId) {
        Solution rejectedSolution = solutionService.rejectSolution(solutionId);
        if (rejectedSolution != null) {
            return new ResponseEntity<>(rejectedSolution, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Escalate a solution issue
    @PostMapping("/{solution_id}/escalate")
    public ResponseEntity<Solution> escalateSolution(@PathVariable("solution_id") Long solutionId) {
        Solution escalatedSolution = solutionService.escalateSolution(solutionId);
        if (escalatedSolution != null) {
            return new ResponseEntity<>(escalatedSolution, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
