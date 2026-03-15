package com.Project.PhishingDetection.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class ScanRequestDto {

    @Getter
    @Setter
    @NotBlank
    private  String  url;





}
