package com.shakthi.firstjobapp.job;

import jakarta.persistence.*;

@Entity
//@Table(name="")
public class Job {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long minExperience;
    private Long salary;


    public Job() {
    }

    public Job(Long id, String title, Long minExperience, Long salary) {
        this.id = id;
        this.title = title;
        this.minExperience = minExperience;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(Long minExperience) {
        this.minExperience = minExperience;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
