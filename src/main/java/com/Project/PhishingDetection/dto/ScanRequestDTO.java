package com.Project.PhishingDetection.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class ScanRequestDTO {

    @Getter
    @Setter
    @NotBlank
    private  String  url;

    @Getter
    @Setter
    @NotBlank
    private String source;





}
