package com.likelion.fillyouinback.skill.repository;

import com.likelion.fillyouinback.skill.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {}
