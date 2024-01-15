package com.topstudy.com.topstudy.service;

import com.topstudy.com.topstudy.dto.SolutionDTO;
import com.topstudy.com.topstudy.enums.SolutionStatus;
import com.topstudy.com.topstudy.model.ClientAccount;
import com.topstudy.com.topstudy.model.Solution;
import com.topstudy.com.topstudy.model.Task;
import com.topstudy.com.topstudy.model.User;
import com.topstudy.com.topstudy.repository.SolutionRepository;
import com.topstudy.com.topstudy.repository.userRepository;
import com.topstudy.com.topstudy.repository.ClientAccountRepository;
import com.topstudy.com.topstudy.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.Date;

@Service
public class SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private ClientAccountRepository clientAccountRepository;

    @Autowired

    private  userRepository UserRepository;



    @Autowired
    private TaskRepository taskRepository;
    @Autowired  // Add this annotation for dependency injection
    private UploadService uploadService;


    @Transactional
    public Solution submitSolution(Long taskId, SolutionDTO solutionDTO) throws IOException {
        Task task = taskRepository.findById(taskId).orElse(null);

        if (task != null) {
            Solution solution = new Solution();
            solution.setTask(task);
            solution.setDescription(solutionDTO.getDescription());
            solution.setDocumentUrl(uploadService.uploadDocument(solutionDTO.getFile()));
            solution.setStatus(SolutionStatus.PENDING);
            solution.setSubmissionDate(new Date());

            return solutionRepository.save(solution);
        }

        return null; // Return null if the task with the specified ID is not found
    }

    @Transactional
    public Solution updateSolution(Long solutionId, SolutionDTO solutionDTO) {
        Solution existingSolution = solutionRepository.findById(solutionId).orElse(null);

        if (existingSolution != null) {
            existingSolution.setDescription(solutionDTO.getDescription());
            return solutionRepository.save(existingSolution);
        }

        return null; // Return null if the solution with the specified ID is not found
    }

    @Transactional(readOnly = true)
    public Solution getSolutionById(Long solutionId) {
        return solutionRepository.findById(solutionId).orElse(null);
    }

    @Transactional
    public Solution acceptSolution(Long solutionId) {
        Solution acceptedSolution = solutionRepository.findById(solutionId).orElse(null);

        if (acceptedSolution != null) {
            acceptedSolution.setStatus(SolutionStatus.ACCEPTED);
            return solutionRepository.save(acceptedSolution);
        }

        return null; // Return null if the solution with the specified ID is not found
    }

    @Transactional
    public Solution rejectSolution(Long solutionId) {
        Solution rejectedSolution = solutionRepository.findById(solutionId).orElse(null);

        if (rejectedSolution != null) {
            rejectedSolution.setStatus(SolutionStatus.REJECTED);
            return solutionRepository.save(rejectedSolution);
        }

        return null; // Return null if the solution with the specified ID is not found
    }

    @Transactional
    public Solution escalateSolution(Long solutionId) {
        Solution escalatedSolution = solutionRepository.findById(solutionId).orElse(null);

        if (escalatedSolution != null) {
            escalatedSolution.setStatus(SolutionStatus.ESCALATED);
            return solutionRepository.save(escalatedSolution);
        }

        return null; // Return null if the solution with the specified ID is not found
    }
}
