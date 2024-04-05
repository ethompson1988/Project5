package com.jobcatalog.jobcatalog.domain;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(long JobId) {
        super("The job with Job Id " + JobId + " was not found.");
    }
}