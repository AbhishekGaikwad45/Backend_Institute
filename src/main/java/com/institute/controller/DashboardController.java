package com.institute.controller;

import com.institute.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "https://venerable-daifuku-51b305.netlify.app")
public class DashboardController {

    @Autowired private StudentRepository studentRepo;
    @Autowired private FacultyRepository facultyRepo;
    @Autowired private BatchRepository batchRepo;

    @Autowired private EnquiryRepository enquiryRepo;
    @Autowired private PaymentRepository paymentRepo;
    @Autowired private AttendanceSummaryRepository attendanceRepo;

    @Autowired private StudentRepository studentRepository;

    @GetMapping("/stats")
    public Map<String, Object> getStats() {

        Map<String, Object> data = new HashMap<>();

        data.put("totalStudents", studentRepo.count());
        data.put("totalFaculty", facultyRepo.count());
        data.put("activeBatches", batchRepo.count());

        // Safe defaults
        data.put("newAdmissions", 0);
        data.put("todaysEnquiries", 0);
        data.put("pendingFeesCount", 0);
        data.put("pendingFeesAmount", 0);
        data.put("inactiveStudents", 0);
        data.put("activeAttendanceStudents", 0);

        try {
            data.put("newAdmissions", studentRepository.countThisMonthAdmissions());
        } catch (Exception ignored) {}

        try {
            data.put("todaysEnquiries", enquiryRepo.countTodayEnquiries());
        } catch (Exception ignored) {}

        try {
            data.put("pendingFeesCount", paymentRepo.pendingStudentCount());
        } catch (Exception ignored) {}

        try {
            data.put("pendingFeesAmount", paymentRepo.pendingAmount());
        } catch (Exception ignored) {}

        try {
            data.put("inactiveStudents", attendanceRepo.inactiveStudents());
        } catch (Exception ignored) {}

        try {
            data.put("activeAttendanceStudents", attendanceRepo.activeStudents());
        } catch (Exception ignored) {}

        return data;
    }

}
    