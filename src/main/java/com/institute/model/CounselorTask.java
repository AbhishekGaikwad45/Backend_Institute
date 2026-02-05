package com.institute.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "counselor_tasks")
public class CounselorTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔑 MAIN IDENTIFIER
    private String counselorEmail;

    private String title;

    @Column(length = 1000)
    private String description;

    private String status;   // PENDING / COMPLETED

    private LocalDate createdDate = LocalDate.now();

    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCounselorEmail() {
        return counselorEmail;
    }

    public void setCounselorEmail(String counselorEmail) {
        this.counselorEmail = counselorEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
