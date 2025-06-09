package com.example.TODOList.service;

import com.example.TODOList.controller.form.ReportForm;
import com.example.TODOList.repository.ReportRepository;
import com.example.TODOList.repository.entity.Report;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    @Autowired
    private final ReportRepository reportRepository;

    public List<ReportForm> findByLimitDateBetweenAndContentAndStatus(String startDate, String endDate, String content, String status) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String StrStartDate = "2020-01-01 00:00:00";
        String StrEndDate = "2100-12-31 23:59:59";

        if (!StringUtils.isBlank(startDate)) {
            StrStartDate = startDate + " 00:00:00";
        }
        if (!StringUtils.isBlank(endDate)) {
            StrEndDate = endDate + " 23:59:59";
        }//df.format(startDate)

        Date StrDate = df.parse(StrStartDate);
        Date EndDate = df.parse(StrEndDate);
        if (StringUtils.isBlank(status) && StringUtils.isBlank(content)) {
            List<Report> results = reportRepository.findByLimitDateBetweenOrderByLimitDateAsc(StrDate, EndDate);
            List<ReportForm> reports = setReportForm(results);
            return reports;
        } else if (StringUtils.isBlank(status) ){
            List<Report> results = reportRepository.findByLimitDateBetweenAndContentOrderByLimitDateAsc(StrDate, EndDate, content);
            List<ReportForm> reports = setReportForm(results);
            return reports;
        } else if (StringUtils.isBlank(content)) {
            int intStatus = Integer.parseInt(status);
            List<Report> results = reportRepository.findByLimitDateBetweenAndStatusOrderByLimitDateAsc(StrDate, EndDate, intStatus);
            List<ReportForm> reports = setReportForm(results);
            return reports;
        }
        int intStatus = Integer.parseInt(status);
        List<Report> results = reportRepository.findByLimitDateBetweenAndContentAndStatusOrderByLimitDateAsc(StrDate, EndDate, content, intStatus);
        List<ReportForm> reports = setReportForm(results);
        return reports;

    }

    private List<ReportForm> setReportForm(List<Report> results) {
        List<ReportForm> reports = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            ReportForm report = new ReportForm();
            Report result = results.get(i);
            report.setId(result.getId());
            report.setContent(result.getContent());
            report.setStatus(result.getStatus());

            // Date → LocalDate に変換
            if (result.getLimitDate() != null) {
                report.setLimitDate(result.getLimitDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate());
            }

            report.setCreatedDate(result.getCreatedDate());
            report.setUpdatedDate(result.getUpdatedDate());
            reports.add(report);
        }
        return reports;
    }

    public void saveReport(ReportForm reqReport) throws ParseException {
        Report saveReport = setReportEntity(reqReport);
        reportRepository.save(saveReport);
    }

    private Report setReportEntity(ReportForm reqReport) throws ParseException {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date LimitDate = df.parse(limit);

        Report report = new Report();
        report.setId(reqReport.getId());
        report.setContent(reqReport.getContent());
        if(reqReport.getStatus() == 0){
            int num = 1;
            report.setStatus(num);
        } else {
            report.setStatus(reqReport.getStatus());
        }
        // LocalDate → Date 変換
        if (reqReport.getLimitDate() != null) {
            Date limitDate = Date.from(
                    reqReport.getLimitDate()
                            .atStartOfDay(ZoneId.systemDefault())
                            .toInstant()
            );
            report.setLimitDate(limitDate);
        } else {
            report.setLimitDate(null);
        }

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
        if(results.get(0) == null){
            ReportForm repo = null;
            return null;
        }
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
