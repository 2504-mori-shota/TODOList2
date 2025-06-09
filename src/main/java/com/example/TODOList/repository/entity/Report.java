package com.example.TODOList.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "tasks")
@Data

@Getter
@Setter
public class Report {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String content;

    @Column(name = "status")
    @Min(value = 1)
    @Max(value = 4)
    private int status;

    @Column(name = "limit_Date")
    private Date limitDate;

    @Column(insertable = false, updatable = false)
    private Date createdDate;

    @Column(name = "updated_Date")
    private Date updatedDate;
    public enum Status {
         未着手, 実行中, ステイ中, 完了
    }
}
