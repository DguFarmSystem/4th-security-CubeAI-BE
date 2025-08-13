package com.cubeai.controller;

import com.cubeai.domain.curriculum.dto.CurriculumResponseDTO;
import com.cubeai.domain.curriculum.entity.Curriculum;
import com.cubeai.repository.CurriculumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/curriculums")
@RequiredArgsConstructor
public class CurriculumController {
    private final CurriculumRepository curriculumRepository;

    @GetMapping
    public List<CurriculumResponseDTO> getAllCurriculums() {
        List<Curriculum> curriculums = curriculumRepository.findAll();
        return curriculums.stream()
                .map(CurriculumResponseDTO::new)
                .collect(Collectors.toList());
    }
}