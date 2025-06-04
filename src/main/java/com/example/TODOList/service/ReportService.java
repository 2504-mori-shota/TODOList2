package com.example.TODOList.service;

import com.example.TODOList.repository.ReportRepository;
import com.example.TODOList.repository.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAllByOrderByLimitDateAsc(); // 次で定義
    }
}
