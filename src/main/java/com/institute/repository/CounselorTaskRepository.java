package com.institute.repository;

import com.institute.model.CounselorTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounselorTaskRepository extends JpaRepository<CounselorTask, Long> {

    List<CounselorTask> findByCounselorEmail(String counselorEmail);
}

