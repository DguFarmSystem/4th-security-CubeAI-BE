package com.cubeai.domain.project.dto.response;

import com.cubeai.domain.project.entity.ProjectHistory;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProjectHistoryResponse(
        Long id,
        String structure,
        LocalDateTime createdAt
) {
    public static ProjectHistoryResponse from(ProjectHistory projectHistory) {
        return ProjectHistoryResponse.builder()
                .id(projectHistory.getProjectHistoryId())
                .structure(projectHistory.getStructure())
                .createdAt(projectHistory.getCreatedAt())
                .build();
    }
}
