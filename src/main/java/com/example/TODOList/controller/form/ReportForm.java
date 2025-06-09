package com.example.TODOList.controller.form;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
@Getter
@Setter
public class ReportForm {

    private int id;

    @NotBlank(message = "タスクを入力してください")
    @Size(max = 140, message = "タスクは140文字以内で入力してください")
    @Pattern(regexp = "^[^　]*$", message = "タスクを入力してください")
    private String content;

    private  int status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "期限を設定してください")
    private Date limitDate;
    @AssertTrue(message = "無効な日付です")
    public boolean isValidLimitDate() {
        if (limitDate == null) return false;
        LocalDate input = limitDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return !input.isBefore(LocalDate.now());
    }

    public LocalDate getLimitLocalDate() {
        return limitDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    private Date createdDate;
    private Date updatedDate;

}
