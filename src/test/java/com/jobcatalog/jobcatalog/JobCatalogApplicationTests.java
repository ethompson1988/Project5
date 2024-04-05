package com.jobcatalog.jobcatalog;

import com.jobcatalog.jobcatalog.domain.Job;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class JobCatalogApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenPostRequestThenBookCreated() {
        var expectedJob = new Job(123456, "Developer",
                "Back-end developer", "Oracle", "Coding", "Programming");

        webTestClient
                .post()
                .uri("/jobs")
                .bodyValue(expectedJob)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Job.class).value(actualJob -> {
                    assertThat(actualJob).isNotNull();
                    assertThat(actualJob.JobId())
                            .isEqualTo(expectedJob.JobId());
                });
    }
}