package com.jobcatalog.jobcatalog.web;

import com.jobcatalog.jobcatalog.domain.JobNotFoundException;
import com.jobcatalog.jobcatalog.domain.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobController.class)
class JobControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    @Test
    void whenGetJobNotExistingThenShouldReturn404() throws Exception {
        long jobId = 123456789;
        given(jobService.viewJobDetails(jobId))
                .willThrow(JobNotFoundException.class);
        mockMvc.perform(get("/jobs/" + jobId))
                .andExpect(status().isNotFound());

    }
}