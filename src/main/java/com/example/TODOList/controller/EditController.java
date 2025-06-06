package com.example.TODOList.controller;

import com.example.TODOList.controller.form.ReportForm;
import com.example.TODOList.service.ReportService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EditController {
    @Autowired
    ReportService reportService;

    @GetMapping("/edit")
    public ModelAndView newContent(
            @RequestParam(name="id", required = false)String strId,
            RedirectAttributes redirectAttributes
        ) {
        ModelAndView mav = new ModelAndView();
        ReportForm reportForm = null;
        if(!StringUtils.isBlank(strId) && strId.matches("^[0-9]*$")) {
            reportForm = reportService.editReport(Integer.parseInt(strId));
            // 準備した空のFormを保管
            mav.addObject("formModel", reportForm);

        }
        if(reportForm == null){
            redirectAttributes.addFlashAttribute("errorMessageForm", "不正なパラメータです");
            return new ModelAndView("redirect:/");
        }

        // 画面遷移先を指定
        mav.setViewName("/edit");
        // mav.addObject("errorMessageForm", errorMessages);
        return mav;
    }
    /*
     * 新規投稿処理
     */
    @PutMapping("/update/{id}")

    public ModelAndView addContent(
            @PathVariable Integer id,
            @RequestParam(name="limit_date" , required = false)String limitDate,
            @ModelAttribute("task")ReportForm report,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) throws ParseException {
        if (result.hasErrors()) {
            //フラッシュメッセージをセット
            redirectAttributes.addFlashAttribute("errorMessageForm", "タスクを入力してください");
            return new ModelAndView("redirect:/edit/{id}");
        }
        report.setId(id);
        // 投稿をテーブルに格納
        reportService.saveReport(report);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

}
