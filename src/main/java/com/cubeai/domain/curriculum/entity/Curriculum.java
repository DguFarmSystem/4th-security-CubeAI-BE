package com.cubeai.domain.curriculum.entity;

import com.cubeai.domain.common.BaseEntity;
import com.cubeai.domain.project.entity.Project;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "curriculum")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Curriculum extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long curriculumId;

    @Column(length = 30, nullable = false)
    private String title;

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Objective> objectives = new ArrayList<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();
}
