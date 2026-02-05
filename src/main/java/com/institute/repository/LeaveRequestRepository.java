package com.institute.repository;

import com.institute.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Integer> {

    List<LeaveRequest> findByStatus(String status);

    List<LeaveRequest> findByStudentId(String studentId);
}

