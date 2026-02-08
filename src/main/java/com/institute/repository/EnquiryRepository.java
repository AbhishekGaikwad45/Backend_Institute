package com.institute.repository;

import com.institute.model.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {

    boolean existsByEmail(String email);
    boolean existsByMobile(String mobile);

    // ✅ TODAY COUNT (SAFE)
    @Query(value = """
        SELECT COALESCE(COUNT(*), 0)
        FROM enquiries
        WHERE created_at IS NOT NULL
          AND DATE(created_at) = CURRENT_DATE
    """, nativeQuery = true)
    Integer countTodayEnquiries();

    // ✅ TODAY ENQUIRIES (SAFE)
    @Query("""
        SELECT e
        FROM Enquiry e
        WHERE e.createdAt IS NOT NULL
          AND FUNCTION('DATE', e.createdAt) = CURRENT_DATE
    """)
    List<Enquiry> findTodayEnquiries();
}


