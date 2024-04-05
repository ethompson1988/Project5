package com.jobcatalog.jobcatalog.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;


public record Job (
        @NotNull(message = "The JobId must be defined.")
        long JobId,
        @NotBlank(
                message = "The job title must be defined."
        )
        String title,
        @NotBlank(message = "The description must be defined.")
        String description,
        @NotBlank(message = "The company name must be defined.")
        String companyName,
        @NotBlank(message = "Skill1 must be defined.")
        String skill1,
        @NotBlank(message = "Skill2 must be defined.")
        String skill2
){}