package com.cubeai.domain.project.dto.response;

import com.cubeai.domain.project.entity.Project;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProjectResponse(
        Long id,
        Long curriculumId,
        String structure,
        LocalDateTime createdAt
) {
    public static ProjectResponse from(Project project) {
        return ProjectResponse.builder()
                .id(project.getProjectId())
                .curriculumId(project.getCurriculum() != null ? project.getCurriculum().getCurriculumId() : null)
                .structure(project.getStructure())
                .createdAt(project.getCreatedAt())
                .build();
    }
}
