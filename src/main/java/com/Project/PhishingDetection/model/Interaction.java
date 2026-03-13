package com.Project.PhishingDetection.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interactions")
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interactionId;
    @Column
    private String originalUrl;

    private String source;

    private Integer riskScore;

    private Integer trustScore;

    private String category;

    private String explanation;

    private LocalDateTime createdAt;





}
