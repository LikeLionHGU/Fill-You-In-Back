package com.likelion.fillyouinback.skill.controller;

import com.likelion.fillyouinback.skill.controller.response.SkillListResponse;
import com.likelion.fillyouinback.skill.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fillyouin/skills")
public class SkillController {
    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<SkillListResponse> getSkillList() {
        return ResponseEntity.ok(new SkillListResponse(skillService.getSkillList()));
    }
}
