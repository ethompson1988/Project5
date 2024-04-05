package com.jobcatalog.jobcatalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobcatalog.jobcatalog.config.JobProperties;

@RestController
public class HomeController {
    private final JobProperties jobProperties;

    public HomeController(JobProperties jobProperties) {
        this.jobProperties = jobProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        return jobProperties.getGreeting();
    }
}