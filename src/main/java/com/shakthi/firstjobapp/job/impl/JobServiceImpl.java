package com.shakthi.firstjobapp.job.impl;

import com.shakthi.firstjobapp.job.Job;
import com.shakthi.firstjobapp.job.JobRepository;
import com.shakthi.firstjobapp.job.JobService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    //List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    Long nextJobId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findall() {
        return jobRepository.findAll();
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void createJob(Job job) {
/*        job.setId(nextJobId++);
        jobs.add(job);*/
        job.setId(nextJobId++);
        jobRepository.save(job);
    }

    @Override
    public boolean deleteJob(Long id) {
/*        for(Job job:jobs){
            if(job.getId()==id){
                jobs.remove(job);
                return true;
            }
        }
        return false;*/
        try{
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }



    }

    @Override
    public boolean updateJob(Long id,Job job) {
/*        List<Job> jobs = jobRepository.findAll();
        for(Job jobsingle:jobs){
            if (jobsingle.getId()==id){
                job.setId(id);
                jobs.set((int) (id-1),job);
                return true;
            }
        }
        return false;*/
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job jobToBeUpdated = jobOptional.get();
            //jobToBeUpdated.setId(job.getId());
            jobToBeUpdated.setSalary(job.getSalary());
            jobToBeUpdated.setTitle(job.getTitle());
            jobToBeUpdated.setMinExperience(job.getMinExperience());
            jobRepository.save(jobToBeUpdated);
            return true;
        }
        return false;
    }
}
