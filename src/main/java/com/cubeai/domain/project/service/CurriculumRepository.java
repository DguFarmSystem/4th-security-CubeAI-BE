package com.cubeai.domain.project.service;

import com.cubeai.domain.curriculum.entity.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

    Optional<Curriculum> findByCurriculumId(Long curriculumId);
}
