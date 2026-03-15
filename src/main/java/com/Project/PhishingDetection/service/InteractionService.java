package com.Project.PhishingDetection.service;


import com.Project.PhishingDetection.model.Interaction;

public interface InteractionService {
    Interaction processscan(String url);
}
