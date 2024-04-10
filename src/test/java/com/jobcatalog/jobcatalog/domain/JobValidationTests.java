package com.jobcatalog.jobcatalog.domain;

import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class JobValidationTests {
    private static Validator validator;
    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var job = new Job(123456, "Title", "Description",
                "CompanyName", "Skill1", "Skill2");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).isEmpty();
    }
    @Test
    void whenJobIdDefinedButIncorrectThenValidationFails() {
        var job = new Job(12345678, "Title", "Description",
                "CompanyName", "Skill1", "Skill2");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The JobId must be 6 digits.");
    }
}