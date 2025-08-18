package com.cubeai.domain.project.dto.response;

import com.cubeai.domain.project.entity.ProjectHistory;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProjectHistoryListResponse(
        Long id,
        LocalDateTime createdAt
) {
    public static ProjectHistoryListResponse from(ProjectHistory projectHistory) {
        return ProjectHistoryListResponse.builder()
                .id(projectHistory.getProjectHistoryId())
                .createdAt(projectHistory.getCreatedAt())
                .build();
    }
}
