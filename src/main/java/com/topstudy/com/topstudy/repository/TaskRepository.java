package com.topstudy.com.topstudy.repository;

import com.topstudy.com.topstudy.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<com.topstudy.com.topstudy.model.Task, Long> {
    List<Task> findByCategory(String category);
}
