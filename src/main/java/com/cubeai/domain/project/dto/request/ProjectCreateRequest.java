package com.cubeai.domain.project.dto.request;

import com.cubeai.domain.curriculum.entity.Curriculum;
import com.cubeai.domain.member.entity.Member;
import com.cubeai.domain.project.entity.Project;

public record ProjectCreateRequest(
        Long curriculumId
) {
    public Project toEntity(Member member, Curriculum curriculum) {
        return Project.builder()
                .member(member)
                .curriculum(curriculum)
                .build();
    }
}
