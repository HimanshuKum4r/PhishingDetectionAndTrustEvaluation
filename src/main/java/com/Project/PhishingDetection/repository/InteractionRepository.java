package com.Project.PhishingDetection.repository;

import com.Project.PhishingDetection.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionRepository extends JpaRepository<Interaction,Long> {
}
