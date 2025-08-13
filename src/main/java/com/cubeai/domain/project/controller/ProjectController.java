package com.cubeai.domain.project.controller;

import com.cubeai.domain.project.dto.request.ProjectCreateRequest;
import com.cubeai.domain.project.dto.request.ProjectSaveRequest;
import com.cubeai.domain.project.dto.response.ProjectHistoryListResponse;
import com.cubeai.domain.project.dto.response.ProjectHistoryResponse;
import com.cubeai.domain.project.dto.response.ProjectListResponse;
import com.cubeai.domain.project.dto.response.ProjectResponse;
import com.cubeai.domain.project.service.ProjectService;
import com.cubeai.global.annotation.Auth;
import com.cubeai.global.common.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createProject(@Auth Long memberId, @RequestBody @Valid ProjectCreateRequest request) {
        ProjectResponse response = projectService.createProject(memberId, request);
        return SuccessResponse.created(response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getProjects(@Auth Long memberId) {
        List<ProjectListResponse> response = projectService.getProjects(memberId);
        return SuccessResponse.ok(response);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<SuccessResponse<?>> getProject(@PathVariable Long projectId) {
        ProjectResponse response = projectService.getProject(projectId);
        return SuccessResponse.ok(response);
    }

    @PostMapping("/{projectId}/save")
    public ResponseEntity<SuccessResponse<?>> saveProject(@PathVariable Long projectId, @RequestBody @Valid ProjectSaveRequest request) {
        ProjectHistoryResponse response = projectService.saveProject(projectId, request);
        return SuccessResponse.ok(response);
    }

    @GetMapping("/{projectId}/history")
    public ResponseEntity<SuccessResponse<?>> getProjectHistory(@PathVariable Long projectId) {
        List<ProjectHistoryListResponse> response = projectService.getProjectHistory(projectId);
        return SuccessResponse.ok(response);
    }

    @GetMapping("/history/{historyId}")
    public ResponseEntity<SuccessResponse<?>> getProjectHistoryDetail(@PathVariable Long historyId) {
        ProjectHistoryResponse response = projectService.getProjectHistoryDetail(historyId);
        return SuccessResponse.ok(response);
    }
}
