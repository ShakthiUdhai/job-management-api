package com.shakthi.firstjobapp.job;

import org.springframework.stereotype.Service;

import java.util.List;

public interface JobService {
    List<Job> findall();
    Job findJobById(Long id);
    void createJob(Job job);
    boolean deleteJob(Long id);
    boolean updateJob(Long id,Job job);
}
