package com.example.TODOList.controller;

import com.example.TODOList.controller.form.ReportForm;
import com.example.TODOList.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;

@Controller
public class AddController {
    @Autowired
    ReportService reportService;

    @GetMapping("/add")
    public ModelAndView newContent() {
        ModelAndView mav = new ModelAndView();
        // form用の空のentityを準備
        ReportForm reportForm = new ReportForm();
        // 画面遷移先を指定
        mav.setViewName("/add");
        // 準備した空のFormを保管
        mav.addObject("formModel", reportForm);
        // mav.addObject("errorMessageForm", errorMessages);
        return mav;
    }
    /*
     * 新規投稿処理
     */
    @PostMapping("/insert")

    public ModelAndView addContent(
            @RequestParam(name="limit_date" , required = false)String limitDate,
            @Valid
            @ModelAttribute("formModel")ReportForm reportForm,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) throws ParseException {
        if (result.hasErrors()) {
            //フラッシュメッセージをセット
           model.addAttribute("formModel", reportForm);
            return new ModelAndView("redirect:/add");
        }
        // 投稿をテーブルに格納
        reportService.saveReport(reportForm, limitDate);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

}
