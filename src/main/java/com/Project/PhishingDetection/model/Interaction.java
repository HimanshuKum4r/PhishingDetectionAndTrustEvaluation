package com.Project.PhishingDetection.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "interactions")
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String interactionToken;

    @Column(name ="original_url")
    private String originalUrl;

    @Column(name ="domain")
    private String domain;

    @Column(name ="source")
    private String source;

    @Column(name ="redirects")
    private Integer Redirects;

    @Column(name = "risk_score")
    private Integer riskScore;

    @Column(name ="trust_score")
    private Integer trustScore;

    @Column(name ="category")
    private String category;

    @Column(name ="explanation", length = 500)
    private String explanation;


    private Boolean warningBypassed;

    @Column(name ="Created_at")
    private LocalDateTime createdAt = LocalDateTime.now();





}
