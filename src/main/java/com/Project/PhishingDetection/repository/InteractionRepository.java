package com.Project.PhishingDetection.repository;

import com.Project.PhishingDetection.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InteractionRepository extends JpaRepository<Interaction,Long> {

    Optional<Interaction> findByInteractionToken(String token);

    Long countByDomain(String url);

    Long countByOriginalUrlAndCategory(String url, String category);
}
