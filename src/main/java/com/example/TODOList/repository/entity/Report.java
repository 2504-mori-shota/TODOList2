package com.example.TODOList.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "limit_Date")
    private Date limitDate;

    @Column(name = "created_Date")
    private Date createdDate;

    @Column(name = "updated_Date")
    private Date updatedDate;

    public enum Status {
        未着手, 実行中, ステイ中, 完了
    }
}
