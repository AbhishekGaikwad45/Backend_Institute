package com.institute.service;

import com.institute.model.LeaveRequest;
import com.institute.model.Student;
import com.institute.repository.LeaveRequestRepository;
import com.institute.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRequestRepository repo;

    @Autowired
    private StudentRepository studentRepo;


    // STUDENT APPLY
    public LeaveRequest applyLeave(LeaveRequest leave) {

        // 🔥 Student ID वरून student शोध
        Student student = studentRepo
                .findByStudentId(leave.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // ✅ studentName set कर
        leave.setStudentName(student.getName());

        leave.setStatus("PENDING");
        return repo.save(leave);
    }


    // COUNSELOR VIEW
    public List<LeaveRequest> getPendingLeaves() {
        return repo.findByStatus("PENDING");
    }

    // COUNSELOR ACTION
    public LeaveRequest updateStatus(
            int id, String status, String remark) {

        LeaveRequest leave = repo.findById(id)
                .orElseThrow();

        leave.setStatus(status);
        leave.setCounselorRemark(remark);

        return repo.save(leave);
    }

    // STUDENT VIEW
    public List<LeaveRequest> getStudentLeaves(String studentId) {
        return repo.findByStudentId(studentId);
    }
}

