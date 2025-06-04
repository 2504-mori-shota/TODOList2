package com.example.TODOList.repository;

import com.example.TODOList.repository.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByOrderByLimitDateAsc();
}
