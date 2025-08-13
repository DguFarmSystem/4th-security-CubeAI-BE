package com.cubeai.domain.curriculum.dto;

import com.cubeai.domain.curriculum.entity.ObjectiveStep;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ObjectiveStepDTO {
    private Long objective_step_id;
    private String title;
    private int priority;

    public ObjectiveStepDTO(ObjectiveStep objectiveStep) {
        this.objective_step_id = objectiveStep.getObjectiveStepId();
        this.title = objectiveStep.getTitle();
        this.priority = objectiveStep.getPriority();
    }
}