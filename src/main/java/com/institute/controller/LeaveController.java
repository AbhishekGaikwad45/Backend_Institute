package com.institute.controller;

import com.institute.model.LeaveRequest;
import com.institute.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/leave")
@CrossOrigin(origins = "http://localhost:3000")
public class LeaveController {

    @Autowired
    private LeaveService service;

    // STUDENT APPLY
    @PostMapping("/apply")
    public ResponseEntity<?> apply(@RequestBody LeaveRequest leave) {
        return ResponseEntity.ok(service.applyLeave(leave));
    }

    // COUNSELOR VIEW PENDING
    @GetMapping("/pending")
    public ResponseEntity<?> pending() {
        return ResponseEntity.ok(service.getPendingLeaves());
    }

    // COUNSELOR APPROVE / REJECT
    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable int id,
            @RequestBody Map<String, String> req) {

        return ResponseEntity.ok(
                service.updateStatus(
                        id,
                        req.get("status"),
                        req.get("remark")
                )
        );
    }

    // STUDENT VIEW STATUS
    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> studentLeaves(
            @PathVariable String studentId) {

        return ResponseEntity.ok(
                service.getStudentLeaves(studentId)
        );
    }
}

