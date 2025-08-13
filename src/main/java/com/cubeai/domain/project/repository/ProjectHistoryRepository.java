package com.cubeai.domain.project.repository;

import com.cubeai.domain.project.entity.Project;
import com.cubeai.domain.project.entity.ProjectHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectHistoryRepository extends JpaRepository<ProjectHistory, Long> {

    List<ProjectHistory> findByProject(Project project);

    Optional<ProjectHistory> findByProjectHistoryId(Long projectHistoryId);
}
