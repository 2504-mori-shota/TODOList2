package com.example.TODOList.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class ReportForm {
    private int id;

    @NotBlank(message = "投稿内容を入力してください")
    private String content;
    private int status;
    private LocalDateTime limit_date;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
