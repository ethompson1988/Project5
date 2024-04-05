package com.jobcatalog.jobcatalog.domain;

import java.util.Optional;

public interface JobRepository {
    Iterable<Job> findAll();
    Optional<Job> findByJobId(long JobId);
    boolean existsByJobId(long JobId);
    Job save(Job job);
    void deleteByJobId(long JobId);
}