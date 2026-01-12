package com.shakthi.firstjobapp.companies;


import com.shakthi.firstjobapp.reviews.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Companies {

    public Companies() {
    }

    @Id
    private Long id;
    private String name;
    private String location;
    @OneToMany
    @JoinColumn(name = "companyId") // points to the column in Review
    private List<Review> reviewList;

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
