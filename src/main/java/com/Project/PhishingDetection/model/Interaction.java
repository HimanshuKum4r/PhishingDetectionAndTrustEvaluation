package com.Project.PhishingDetection.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    private Long interactionId;

    private String originalUrl;

    private String source;

    private Integer riskScore;

    private Integer trustScore;

    private String category;

    private String explanation;

    private LocalDateTime createdAt;





}
