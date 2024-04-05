package com.jobcatalog.jobcatalog.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import com.jobcatalog.jobcatalog.domain.Job;
import com.jobcatalog.jobcatalog.domain.JobRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryJobRepository implements JobRepository {
    private static final Map<Long, Job> jobs =
            new ConcurrentHashMap<>();
    @Override
    public Iterable<Job> findAll() {
        return jobs.values();
    }
    @Override
    public Optional<Job> findByJobId(long JobId) {
        return existsByJobId(JobId) ? Optional.of(jobs.get(JobId)) :
                Optional.empty();
    }

    @Override
    public boolean existsByJobId(long JobId) {
        return jobs.get(JobId) != null;
    }

    @Override
    public Job save(Job job) {
        jobs.put(job.JobId(), job);
        return job;
    }

    @Override
    public void deleteByJobId(long JobId) {
        jobs.remove(JobId);
    }
}