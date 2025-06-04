package com.example.TODOList.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ReportForm {

    private int id;

    @NotBlank
    private String content;
    @NotBlank
    private Date limitDate;
    private Date createdDate;
    private Date updatedDate;

}