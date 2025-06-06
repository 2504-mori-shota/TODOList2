package com.example.TODOList.controller.form;

import jakarta.validation.constraints.*;
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

    @NotEmpty
    private Date limitDate;
    public LocalDate getLimitLocalDate() {
        return limitDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    private Date createdDate;
    private Date updatedDate;

}
