package com.example.TODOList.controller.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
@Getter
@Setter
public class ReportForm {

    private int id;

    @NotBlank
    @Size(max = 140)
    private String content;

    private  int status;


    private Date limitDate;
    //  LocalDate に変換して返すメソッド
    public LocalDate getLimitLocalDate() {
        return limitDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    private Date createdDate;
    private Date updatedDate;

}
