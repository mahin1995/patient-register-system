package com.assessment.repository;

import com.assessment.entity.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    @Query(value = """
            select p from Prescription p where ( ( :fromDate is null and :toDate is null) or   p.createdAt between :fromDate and :toDate )
            """)
    Page<Prescription> findByPrescriptionDateBetween(LocalDate fromDate, LocalDate toDate, PageRequest pageRequest);
}
