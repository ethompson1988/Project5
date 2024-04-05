package com.jobcatalog.jobcatalog.domain;

import org.springframework.stereotype.Service;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    public Iterable<Job> viewJobList() {
        return jobRepository.findAll();
    }

    public Job viewJobDetails(long JobId) {
        return jobRepository.findByJobId(JobId)
                .orElseThrow(() -> new JobNotFoundException(JobId));
    }

    public Job addJobToCatalog(Job job) {
        if (jobRepository.existsByJobId(job.JobId())) {
            throw new JobAlreadyExistsException(job.JobId());
        }
        return jobRepository.save(job);
    }

    public void removeJobFromCatalog(long JobId) {
        jobRepository.deleteByJobId(JobId);
    }

    public Job editJobDetails(long JobId, Job job) {
        return jobRepository.findByJobId(JobId)
                .map(existingJob -> {
                            var jobToUpdate = new Job(
                                    existingJob.JobId(),
                                    job.title(),
                                    job.description(),
                                    job.companyName(),
                                    job.skill1(),
                                    job.skill2());
                            return jobRepository.save(jobToUpdate);
                        })
                .orElseGet(() -> addJobToCatalog(job));
                }
    }
