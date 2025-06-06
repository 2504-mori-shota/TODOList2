package com.example.TODOList.repository;

import com.example.TODOList.repository.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByLimitDateBetweenAndContentAndStatusOrderByLimitDateAsc(Date startDate, Date endDate, String content, int status);
    List<Report> findByLimitDateBetweenAndContentOrderByLimitDateAsc(Date startDate, Date endDate, String content);
    List<Report> findByLimitDateBetweenAndStatusOrderByLimitDateAsc(Date startDate, Date endDate, int status);
    List<Report> findByLimitDateBetweenOrderByLimitDateAsc(Date startDate, Date endDate);
}
