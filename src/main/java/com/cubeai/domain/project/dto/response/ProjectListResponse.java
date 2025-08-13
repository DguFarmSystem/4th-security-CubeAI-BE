package com.cubeai.domain.project.dto.response;

import com.cubeai.domain.project.entity.Project;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProjectListResponse(
        Long id,
        Long curriculumId,
        LocalDateTime createdAt
) {
    public static ProjectListResponse from(Project project) {
        return ProjectListResponse.builder()
                .id(project.getProjectId())
                .curriculumId(project.getCurriculum() != null ? project.getCurriculum().getCurriculumId() : null)
                .createdAt(project.getCreatedAt())
                .build();
    }
}
