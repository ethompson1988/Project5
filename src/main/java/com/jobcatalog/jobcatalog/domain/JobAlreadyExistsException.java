package com.jobcatalog.jobcatalog.domain;

public class JobAlreadyExistsException extends RuntimeException {
    public JobAlreadyExistsException(long JobId) {
        super("A job with Job Id " + JobId + " already exists.");
    }
}