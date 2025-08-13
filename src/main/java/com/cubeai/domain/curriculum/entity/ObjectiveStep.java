package com.cubeai.domain.curriculum.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "objective_step")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ObjectiveStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long objectiveStepId;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "objective_id", nullable = false)
    private Objective objective;
}
