package com.shakthi.jobmanagement.job;

import java.util.List;

public interface JobService {
    List<Job> findall();
    Job findJobById(Long id);
    void createJob(Job job);
    void deleteJob(Long id);
    void updateJob(Long id,Job job);
}
