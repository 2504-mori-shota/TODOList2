package com.example.TODOList.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tasks")
@Data

public class Report {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime limitDate;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public enum Status {
        未着手, 実行中, ステイ中, 完了
    }
}

