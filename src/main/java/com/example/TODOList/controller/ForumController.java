package com.example.TODOList.controller;


import com.example.TODOList.controller.form.ReportForm;
import com.example.TODOList.repository.entity.Report;
import com.example.TODOList.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ForumController {
    @Autowired
    ReportService reportService;

    /*
     * 投稿内容表示処理
     */
    @GetMapping("/")
    public String showTop(Model model) {
        List<Report> tasks = reportService.getAllReports();
        model.addAttribute("tasks", tasks);
        model.addAttribute("statuses", Report.Status.values()); // ステータスプルダウン用
        model.addAttribute("today", LocalDate.now()); // 今日の日付も渡してあげるわよ
        return "top";
    }
}
