package com.jobcatalog.jobcatalog.demo;

import com.jobcatalog.jobcatalog.domain.Job;
import com.jobcatalog.jobcatalog.domain.JobRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class JobDataLoader {
    private final JobRepository jobRepository;
    public JobDataLoader(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadJobTestData() {
        var job1 = new Job(123456, "Developer", "Back-end software engineer",
                "Oracle", "Java", "SQL");
        var job2 = new Job(123457, "Programmer", "Full-stack software engineer",
                "Lockheed Martin", "Python", "Web development");
        jobRepository.save(job1);
        jobRepository.save(job2);
    }
}