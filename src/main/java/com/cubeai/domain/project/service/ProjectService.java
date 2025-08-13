package com.cubeai.domain.project.service;

import com.cubeai.domain.project.dto.request.ProjectSaveRequest;
import com.cubeai.domain.project.dto.response.ProjectHistoryListResponse;
import com.cubeai.domain.project.dto.response.ProjectHistoryResponse;
import com.cubeai.domain.project.entity.Project;
import com.cubeai.domain.project.entity.ProjectHistory;
import com.cubeai.domain.project.repository.ProjectHistoryRepository;
import com.cubeai.domain.project.repository.ProjectRepository;
import com.cubeai.global.error.ErrorCode;
import com.cubeai.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectHistoryRepository projectHistoryRepository;

    @Transactional
    public ProjectHistoryResponse saveProject(Long projectId, ProjectSaveRequest request) {
        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PROJECT_NOT_FOUND));
        ProjectHistory savedProjectHistory = ProjectHistory.builder()
                .project(project)
                .structure(request.structure())
                .build();
        return ProjectHistoryResponse.from(savedProjectHistory);
    }

    public List<ProjectHistoryListResponse> getProjectHistory(Long projectId) {
        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PROJECT_NOT_FOUND));
        List<ProjectHistory> projectHistories = projectHistoryRepository.findByProject(project);
        return projectHistories.stream()
                .map(ProjectHistoryListResponse::from)
                .collect(Collectors.toList());
    }
}
