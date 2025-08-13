package com.cubeai.domain.curriculum.dto;

import com.cubeai.domain.curriculum.entity.Curriculum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CurriculumResponseDTO {
    private Long curriculumId;

    private String title;
    private List<ObjectiveDTO> objectives;

    public CurriculumResponseDTO(Curriculum curriculum) {
        this.curriculumId = curriculum.getCurriculumId();
        this.title = curriculum.getTitle();
        this.objectives = curriculum.getObjectives().stream()
                .map(ObjectiveDTO::new)
                .collect(Collectors.toList());
    }
}