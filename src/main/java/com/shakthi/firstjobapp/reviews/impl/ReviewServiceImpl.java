package com.shakthi.firstjobapp.reviews.impl;

import com.shakthi.firstjobapp.companies.Companies;
import com.shakthi.firstjobapp.companies.CompaniesRepository;
import com.shakthi.firstjobapp.companies.impl.CompaniesServiceImpl;
import com.shakthi.firstjobapp.reviews.Review;
import com.shakthi.firstjobapp.reviews.ReviewRepository;
import com.shakthi.firstjobapp.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;
    CompaniesServiceImpl companiesService;

    private static Long reviewId = 1L;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompaniesServiceImpl companiesService) {
        this.reviewRepository = reviewRepository;
        this.companiesService = companiesService;
    }

    @Override
    public List<Review> getReviewByCompanyId(Long companyId) {
        Companies company = companiesService.findCompanyById(companyId);
        if(company==null){
            return null;
        }
        return company.getReviewList();
    }

    @Override
    public Boolean addReviewByCompanyId(Review review, Long companyId) {


        Companies company = companiesService.findCompanyById(companyId);
        if(company==null){
            return false;
        }
        List<Review> reviews = company.getReviewList();
        review.setReviewId(reviewId++);
        review.setCompanyId(companyId);
        reviewRepository.save(review);
        reviews.add(review);
        company.setReviewList(reviews);
        companiesService.updateCompany(companyId,company);
        return true;

    }

    @Override
    public Review getReviewByReviewId(Long companyId, Long reviewId) {
        Companies company = companiesService.findCompanyById(companyId);
        if(company==null){
            return null;
        }
        List<Review> reviews = company.getReviewList();
        for(Review review : reviews){
            if(Objects.equals(review.getReviewId(), reviewId)){
                return review;
            }
        }
        return null;
    }

    @Override
    public Boolean updateReviewByReviewId(Long companyId, Long reviewId, Review review) {
        Companies company = companiesService.findCompanyById(companyId);
        if(company==null){
            return false;
        }
        List<Review> reviews = company.getReviewList();
        for(int i = 0 ; i<reviews.size() ; i++){
            if(Objects.equals(reviews.get(i).getReviewId(), reviewId)){
                reviews.set(i,review);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteReviewByReviewId(Long companyId, Long reviewId) {
        Companies company = companiesService.findCompanyById(companyId);
        if(company==null){
            return false;
        }
        List<Review> reviews = company.getReviewList();
        for(int i = 0 ; i<reviews.size() ; i++){
            if(Objects.equals(reviews.get(i).getReviewId(), reviewId)){
                reviews.remove(i);
                return true;
            }
        }

        return false;

    }
}
