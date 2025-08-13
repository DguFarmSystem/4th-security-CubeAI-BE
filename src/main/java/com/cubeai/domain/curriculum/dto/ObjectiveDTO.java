package com.cubeai.domain.curriculum.dto;

import com.cubeai.domain.curriculum.entity.Objective;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ObjectiveDTO {
    private Long objectiveId;
    private String title;
    private int priority;
    private List<ObjectiveStepDTO> objectiveSteps;

    public ObjectiveDTO(Objective objective) {
        this.objectiveId = objective.getObjectiveId();
        this.title = objective.getTitle();
        this.priority = objective.getPriority();
        this.objectiveSteps = objective.getObjectiveSteps().stream()
                .map(ObjectiveStepDTO::new)
                .collect(Collectors.toList());
    }
}