package com.example.TODOList.controller;

import com.example.TODOList.controller.form.ReportForm;
import com.example.TODOList.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class TopController {
    @Autowired
    ReportService reportService;

    /*
     * 投稿内容表示処理
     */
    @GetMapping
    public ModelAndView top(@RequestParam(value = "startDate", required = false) String startDate,
                            @RequestParam(value = "endDate", required = false) String endDate) {
        ModelAndView mav = new ModelAndView();
        // 投稿を全件取得
        List<ReportForm> contentData = reportService.findAllReport(startDate, endDate);
        // 画面遷移先を指定
        mav.setViewName("/top");
        // 投稿データオブジェクトを保管
        mav.addObject("contents", contentData);
        return mav;
    }

    /*
     * 新規投稿処理
     * フォームからデータが送られていたら、バリエーションでエラーがあれば入力画面に戻す。
     * なければデータを保存してトップページにリダイレクトする流れ。
     */
    @PostMapping("/add")
    public ModelAndView addContent(@ModelAttribute("formModel") @Valid ReportForm reportForm, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            mav.setViewName("/new");
            return mav;

        }
//        // 投稿をテーブルに格納
//        reportService.saveReport(reportForm);
//        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }
}
