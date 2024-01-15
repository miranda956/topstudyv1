package com.topstudy.com.topstudy.controller;

import com.topstudy.com.topstudy.dto.TaskDTO;
import com.topstudy.com.topstudy.model.Task;
import com.topstudy.com.topstudy.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService; // You'll need to create this service

    // Create a new task
    @PostMapping("/create")
    public ResponseEntity<?> createTask(
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart("category") String category,
            @RequestPart("duration") String duration,
            @RequestPart("wordCount") String wordCount,
            @RequestPart("budget") String budget,
            @RequestPart("file") MultipartFile file) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName(name);
        taskDTO.setDescription(description);
        taskDTO.setCategory(category);
        taskDTO.setWordCount(wordCount);
        taskDTO.setDuration(duration);
        taskDTO.setBudget(budget);
        taskDTO.setFile(file);

        Task createdTask = taskService.createTask(taskDTO);

        if (createdTask != null) {
            return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create task", HttpStatus.BAD_REQUEST);
        }
    }

    // Get all tasks
    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        if (!tasks.isEmpty()) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update task details by ID
    @PutMapping("/{task_id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable("task_id") Long taskId,
            @RequestBody TaskDTO taskDTO) throws IOException {
        Task updatedTask = taskService.updateTask(taskId, taskDTO);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get task details by ID
    @GetMapping("/{task_id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("task_id") Long taskId) {
        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // List tasks by category
    @GetMapping
    public ResponseEntity<List<Task>> getTasksByCategory(@RequestParam("category") String category) {
        List<Task> tasks = taskService.getTasksByCategory(category);
        if (!tasks.isEmpty()) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
