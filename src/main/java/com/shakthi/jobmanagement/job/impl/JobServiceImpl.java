package com.shakthi.jobmanagement.job.impl;

import com.shakthi.jobmanagement.job.Job;
import com.shakthi.jobmanagement.job.JobNotFoundException;
import com.shakthi.jobmanagement.job.JobRepository;
import com.shakthi.jobmanagement.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    //List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    //Long nextJobId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findall() {
        return jobRepository.findAll();
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job with id : "+id+" Not found"));
    }

    @Override
    public void createJob(Job job) {
/*        job.setId(nextJobId++);
        jobs.add(job);*/
        //job.setId(nextJobId++);
        jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id) {
        Job job = findJobById(id);
        if(job==null){
            throw new JobNotFoundException("Job with id : "+id+" Not found");
        }
        else{
            jobRepository.deleteById(id);
            //return true;
        }
        /*for(Job job:jobs){
            if(job.getId()==id){
                jobs.remove(job);
                return true;
            }
        }
        return false;*/
        /*try{
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }*/



    }

    @Override
    public void updateJob(Long id, Job job) {
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
        }
        throw new JobNotFoundException("Job with id : "+id+" Not found");
    }
}
