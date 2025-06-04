package com.example.TODOList.repository;

import com.example.TODOList.repository.entity.Report;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportRepository {
    List<Report> findByCreatedAtBetweenOrderByUpdatedAtDesc(LocalDateTime startDate, LocalDateTime endDate);
}
