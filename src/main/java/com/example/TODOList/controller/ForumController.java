package com.example.TODOList.controller;


import com.example.TODOList.controller.form.ReportForm;
import com.example.TODOList.repository.ReportRepository;
import com.example.TODOList.repository.entity.Report;
import com.example.TODOList.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ForumController {
    @Autowired
    ReportService reportService;

    @Autowired
    ReportRepository reportRepository;

    /*
     * 投稿内容表示処理
     */
    @GetMapping("/")
    public String showTop( @RequestParam(name = "startDate", required = false)String startDate ,
                           @RequestParam(name="endDate", required = false )String endDate,
                           @RequestParam(name="status", required = false)String status,
                           @RequestParam(name="content", required = false)String content,
                           @Valid
                           Model model
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();
        List<ReportForm> tasks = reportService.findByLimitDateBetweenAndContentAndStatus(startDate , endDate, content, status);
        model.addAttribute("tasks", tasks);
        model.addAttribute("statuses", Report.Status.values()); // ステータスプルダウン用
        model.addAttribute("today", LocalDate.now()); // 今日の日付も渡してあげるわよ
        return "top";
    }
    @GetMapping("/task/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        reportRepository.deleteById(id);
        return "redirect:/"; // 一覧画面に戻す
    }
    @PostMapping("/task/updateStatus")
    public String updateStatus(@RequestParam Long id,@RequestParam int status){
        reportService.updateStatus(id, status);
        return "redirect:/";
    }

}
