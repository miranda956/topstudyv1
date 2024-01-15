package com.topstudy.com.topstudy.service;

import com.topstudy.com.topstudy.dto.TaskDTO;
import com.topstudy.com.topstudy.model.Task;
import com.topstudy.com.topstudy.repository.TaskRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import  com.topstudy.com.topstudy.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import com.topstudy.com.topstudy.service.UploadService;

import java.io.IOException;
import java.util.List;
import java.security.SecureRandom;


@Service
public class TaskService {

    @Resource
    private TaskRepository taskRepository;
    @Autowired  // Add this annotation for dependency injection
    private UploadService uploadService;

    private static final String ALLOWED_CHARACTERS = "OR0123456789";
    private static final SecureRandom random = new SecureRandom();

    private static String generatetaskId(int length) {
        StringBuilder staffId = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            staffId.append(randomChar);
        }
        return staffId.toString();
    }


    @Transactional
    public Task createTask(TaskDTO taskDTO) {
        try {
            Task task = new Task();
            task.setTaskId(generatetaskId(8));
            task.setName(taskDTO.getName());
            task.setDescription(taskDTO.getDescription());
            task.setCategory(taskDTO.getCategory());
            String trimmedDuration = taskDTO.getDuration().trim();
            task.setDuration(Integer.parseInt(trimmedDuration));
            task.setWordCount(taskDTO.getWordCount());
            task.setBudget(taskDTO.getBudget());
            task.setDocumentUrl(uploadService.uploadDocument(taskDTO.getFile()));

            // Set other task properties as needed

            return taskRepository.save(task);
        } catch (IOException e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Error uploading document", e);
        }
    }


    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            task.calculateRemainingTime();
        }
        return tasks;
    }

    @Transactional
    public Task updateTask(Long taskId, TaskDTO taskDTO) throws IOException {
        Task existingTask = taskRepository.findById(taskId).orElse(null);

        if (existingTask != null) {
            existingTask.setName(taskDTO.getName());
            existingTask.setDescription(taskDTO.getDescription());
            existingTask.setCategory(taskDTO.getCategory());
            existingTask.setBudget(taskDTO.getBudget());
            existingTask.setWordCount(taskDTO.getWordCount());
            String trimmedDuration = taskDTO.getDuration().trim();
            existingTask.setDuration(Integer.parseInt(trimmedDuration));
            existingTask.setDocumentUrl(uploadService.uploadDocument(taskDTO.getFile()));



            // Update other task properties as needed

            return taskRepository.save(existingTask);
        }

        return null; // Return null if the task with the specified ID is not found
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Long taskId) {
        // Retrieve the task by its ID from the repository
        Task task = taskRepository.findById(taskId).orElse(null);

        // If the task is found, calculate its remaining time
        if (task != null) {
            task.calculateRemainingTime();
        }

        // Return the task with calculated remaining time
        return task;
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksByCategory(String category) {
        // Retrieve tasks by category from the repository
        List<Task> tasks = taskRepository.findByCategory(category);

        // Calculate remaining time for each task
        for (Task task : tasks) {
            task.calculateRemainingTime();
        }

        // Return the list of tasks with calculated remaining time
        return tasks;
    }
}