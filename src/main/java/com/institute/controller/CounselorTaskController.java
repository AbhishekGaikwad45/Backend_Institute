package com.institute.controller;

import com.institute.model.CounselorTask;

import com.institute.repository.CounselorTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "https://venerable-daifuku-51b305.netlify.app")
public class CounselorTaskController {

    @Autowired
    private CounselorTaskRepository repo;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CounselorTask task) {
        task.setStatus("PENDING");
        task.setCreatedDate(LocalDate.now());
        return ResponseEntity.ok(repo.save(task));
    }

    // 🔥 ALL TASKS FOR ALL COUNSELORS
    @GetMapping("/all")
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<?> complete(@PathVariable Long id) {
        CounselorTask task = repo.findById(id).orElseThrow();
        task.setStatus("COMPLETED");
        return ResponseEntity.ok(repo.save(task));
    }

    @PutMapping("/pending/{id}")
    public ResponseEntity<?> pending(@PathVariable Long id) {
        CounselorTask task = repo.findById(id).orElseThrow();
        task.setStatus("PENDING");
        return ResponseEntity.ok(repo.save(task));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}



