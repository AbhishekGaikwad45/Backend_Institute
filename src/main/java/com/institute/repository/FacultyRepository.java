package com.institute.repository;

import com.institute.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    Faculty findByFacultyCode(String facultyCode);

    boolean existsByEmail(String email);


    boolean existsByMobile(String mobile);
    Faculty findByEmailIgnoreCase(String email);

    @Query(
            value = """
        SELECT * FROM faculty
        WHERE birth_date IS NOT NULL
        AND MONTH(STR_TO_DATE(birth_date, '%Y-%m-%d')) = MONTH(CURDATE())
        AND DAY(STR_TO_DATE(birth_date, '%Y-%m-%d')) = DAY(CURDATE())
      """,
            nativeQuery = true
    )
    List<Faculty> findTodayBirthdays();

    Faculty findByEmail(String email);

    @Query(value = "SELECT faculty_code FROM faculty ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String findLastFacultyCode();


}
