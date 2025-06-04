package com.example.TODOList.controller;

import com.example.TODOList.controller.form.ReportForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AddController {

    @GetMapping("/new")
    public ModelAndView newContent() {
        ModelAndView mav = new ModelAndView();
        // form用の空のentityを準備
        ReportForm reportForm = new ReportForm();
        // 画面遷移先を指定
        mav.setViewName("/new");
        // 準備した空のFormを保管
        mav.addObject("formModel", reportForm);
        // mav.addObject("errorMessageForm", errorMessages);
        return mav;
    }
    /*
     * 新規投稿処理
     */
    @PostMapping("/add")
    public ModelAndView addContent(
            @Valid @ModelAttribute("formModel") ReportForm reportForm,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model){
        if (result.hasErrors()) {
            //フラッシュメッセージをセット
            redirectAttributes.addFlashAttribute("errorMessageForm", "タスクを入力してください");
            return new ModelAndView("redirect:/new");
        }
        // 投稿をテーブルに格納
        //reportService.saveReport(reportForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

}
