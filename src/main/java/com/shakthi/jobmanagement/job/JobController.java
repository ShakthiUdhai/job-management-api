package com.shakthi.jobmanagement.job;

import com.shakthi.jobmanagement.job.impl.JobServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    private JobServiceImpl jobService;

    public JobController(JobServiceImpl jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findall() {
        if (jobService.findall() != null) {
            return new ResponseEntity<>(jobService.findall(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>(new String("Job Added Successfully"),HttpStatus.OK);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        /*if (jobService.findJobById(id) != null) {
            return new ResponseEntity<>(jobService.findJobById(id), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);*/
        return new ResponseEntity<>(jobService.findJobById(id), HttpStatus.OK);
    }

    @DeleteMapping("/jobs/delete/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        /*boolean deleted = jobService.deleteJob(id);
        if (deleted) {
            return new ResponseEntity<>("Job with id : " + id + " deleted successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job with id : "+id+" not found",HttpStatus.NOT_FOUND);*/
        jobService.deleteJob(id);
        return new ResponseEntity<>("Job with id : " + id + " deleted successfully!", HttpStatus.OK);
    }

    @PutMapping("/jobs/update/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id,@RequestBody Job job){
        jobService.updateJob(id,job);
        /*if(updated){
            return new ResponseEntity<>("Job updated successfully!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Job with id - "+id+" not found!",HttpStatus.NOT_FOUND);*/
        return new ResponseEntity<>("Job updated successfully!",HttpStatus.OK);
    }

}
