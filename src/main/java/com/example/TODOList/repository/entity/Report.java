package com.example.TODOList.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Report {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String content;

    @Column
    private int status;

    @Column(name = "updated_at", nullable = false, insertable = false)
    private LocalDateTime limit_date;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
    @Column(name = "updated_at", nullable = false, insertable = false)
    private LocalDateTime updatedAt;
    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
