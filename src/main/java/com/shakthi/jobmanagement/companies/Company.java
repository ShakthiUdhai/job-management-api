package com.shakthi.jobmanagement.companies;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shakthi.jobmanagement.job.Job;
import com.shakthi.jobmanagement.reviews.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {

    public Company() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Review> reviewList;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
