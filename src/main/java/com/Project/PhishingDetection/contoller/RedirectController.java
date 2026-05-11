package com.Project.PhishingDetection.contoller;

import com.Project.PhishingDetection.service.RedirectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/r")
public class RedirectController {
    private final RedirectService service;

    @GetMapping("/{token}")
    public ResponseEntity<?> redirect(
            @PathVariable String token){

        return service.handleRedirect(token);
    }

    @PostMapping("/proceed/{token}")
    public ResponseEntity<?> proceed(
            @PathVariable String token){

        return service.proceed(token);
    }
}
