package com.cubeai.domain.curriculum.repository;

import com.cubeai.domain.curriculum.entity.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

    Optional<Curriculum> findByCurriculumId(Long curriculumId);
}
