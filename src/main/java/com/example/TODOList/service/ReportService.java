package com.example.TODOList.service;

import com.example.TODOList.controller.form.ReportForm;
import com.example.TODOList.repository.ReportRepository;
import com.example.TODOList.repository.entity.Report;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    @Autowired
    private final ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAllByOrderByLimitDateAsc();
    }

    private List<ReportForm> setReportForm(List<Report> results) {
        List<ReportForm> reports = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            ReportForm report = new ReportForm();
            Report result = results.get(i);
            report.setId(result.getId());
            report.setContent(result.getContent());
            report.setLimitDate(result.getLimitDate());
            report.setCreatedDate(result.getCreatedDate());
            report.setUpdatedDate(result.getUpdatedDate());
            reports.add(report);
        }
        return reports;
    }

    public void saveReport(ReportForm reqReport, String limit) throws ParseException {
        Report saveReport = setReportEntity(reqReport, limit);
        reportRepository.save(saveReport);
    }
    private Report setReportEntity(ReportForm reqReport, String limit) throws ParseException {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date LimitDate = df.parse(limit);
        Report report = new Report();
        report.setId(reqReport.getId());
        report.setContent(reqReport.getContent());
        report.setStatus(reqReport.getStatus());
        report.setLimitDate(LimitDate);
        report.setCreatedDate(reqReport.getCreatedDate());
        report.setUpdatedDate(currentTime);
        return report;
    }

    /*
     * レコード1件取得
     */
    public ReportForm editReport(Integer id) {
        List<Report> results = new ArrayList<>();
        results.add((Report) reportRepository.findById(Long.valueOf(id)).orElse(null));
        List<ReportForm> reports = setReportForm(results);
        /*
         * 上記の処理は仮にデータを2つ以上取得してきた際にバグがおこらないように配列にデータを入れ、
         * その中から最初のデータを取得するようにしている。
         */
        return reports.get(0);
    }
    @Transactional
    public void updateStatus(Long id, int status) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("指定されたタスクが見つかりません: ID=" + id));
        report.setStatus(status);
        report.setUpdatedDate(new Date());
        reportRepository.save(report);
    }

}
