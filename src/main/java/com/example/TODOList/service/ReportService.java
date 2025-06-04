package com.example.TODOList.service;

import com.example.TODOList.controller.form.ReportForm;
import com.example.TODOList.repository.ReportRepository;
import com.example.TODOList.repository.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;
    /*
     * レコード全件取得処理
     */
    public List<ReportForm> findAllReport(String startDate, String endDate) {
        // 現在日時を取得
        LocalDateTime nowDate = LocalDateTime.now();

        LocalDateTime start = null;
        LocalDateTime end = null;

        if (startDate != null && !startDate.isEmpty()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            startDate = startDate + " 11:11:11";
            start = LocalDateTime.parse(startDate, dtf);
        } else {
            start = LocalDateTime.parse("2020-01-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        if (endDate != null && !endDate.isEmpty()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            endDate = endDate + " 23:59:59";
            end = LocalDateTime.parse(endDate, dtf);
        } else {
            end = nowDate;
        }
        // ReportRepository を使用して、指定した期間のレポートを取得
        List<Report> results = reportRepository.findByCreatedAtBetweenOrderByUpdatedAtDesc(start, end);
        //results = reportRepository.findAllByOrderByPriceDesc(start, end);
        // ReportForm に変換
        List<ReportForm> reports = setReportForm(results);
        return reports;
    }

    /*
     * DBから取得したデータをFormに設定
     */
    private List<ReportForm> setReportForm(List<Report> results) {
        List<ReportForm> reports = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            ReportForm report = new ReportForm();
            Report result = results.get(i);
            report.setId(result.getId());
            report.setContent(result.getContent());
            //reportId に紐づくコメントを取得する処理
            reports.add(report);
        }
        return reports;
    }
    /*
     * レコード追加
     */
//    public void saveReport(ReportForm reqReport) {
//        Report saveReport = setReportEntity(reqReport);
//        reportRepository.save(saveReport);
//    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Report setReportEntity(ReportForm reqReport) {
        Report report = new Report();
        //
        report.setId(reqReport.getId());
        report.setContent(reqReport.getContent());
        return report;
    }
    /*
     * レコード1件取得
     */
//    //idに対応する投稿データを取得し、それをReportFormg型に変換して返す処理。
//    public ReportForm editReport(Integer id) {
//        //<Report>型のオブジェクトを格納するための空リストresultsを作成
//        List<Report> results = new ArrayList<>();
//        //指定されたidに対応するReportオブジェクトを取得
//        //取得したReportオブジェクトをresultsに追加
//        results.add((Report) reportRepository.findById(id).orElse(null));
//        //setReportForm メソッドを呼び出し、resultsリストを引数として渡す
//        //Report オブジェクトのリストを ReportForm オブジェクトのリストに変換する処理を行う
//        List<ReportForm> reports = setReportForm(results);
//        return reports.get(0);
//    }
}