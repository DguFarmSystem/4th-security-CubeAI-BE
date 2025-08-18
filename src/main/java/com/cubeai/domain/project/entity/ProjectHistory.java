package com.cubeai.domain.project.entity;

import com.cubeai.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "project_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectHistoryId;

    @Lob
    private String structure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Builder
    public ProjectHistory(String structure, Project project) {
        this.structure = structure;
        this.project = project;
    }
}
