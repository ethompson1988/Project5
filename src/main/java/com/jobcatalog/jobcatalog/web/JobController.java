package com.jobcatalog.jobcatalog.web;

import com.jobcatalog.jobcatalog.domain.Job;
import com.jobcatalog.jobcatalog.domain.JobService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public Iterable<Job> get() {
        return jobService.viewJobList();
    }

    @GetMapping("{JobId}")
    public Job getByJobId(@PathVariable long JobId) {
        return jobService.viewJobDetails(JobId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job post(@Valid @RequestBody Job job) {
        return jobService.addJobToCatalog(job);
    }

    @DeleteMapping("{JobId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long JobId) {
        jobService.removeJobFromCatalog(JobId);
    }

    @PutMapping("{JobId}")
    public Job put(@PathVariable long JobId, @Valid @RequestBody Job job) {
        return jobService.editJobDetails(JobId, job);
    }
}