package com.likelion.fillyouinback.affiliation.controller;

import com.likelion.fillyouinback.affiliation.controller.response.AffiliationListResponse;
import com.likelion.fillyouinback.affiliation.service.AffiliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fillyouin/affiliations")
public class AffiliationController {
    private final AffiliationService affiliationService;

    @GetMapping
    public ResponseEntity<AffiliationListResponse> getAffiliationList() {
        return ResponseEntity.ok(new AffiliationListResponse(affiliationService.getAffiliationList()));
    }
}
