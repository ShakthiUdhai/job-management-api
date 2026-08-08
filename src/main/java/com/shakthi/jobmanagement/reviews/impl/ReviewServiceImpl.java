package com.shakthi.jobmanagement.reviews.impl;

import com.shakthi.jobmanagement.companies.Company;
import com.shakthi.jobmanagement.companies.impl.CompanyServiceImpl;
import com.shakthi.jobmanagement.reviews.Review;
import com.shakthi.jobmanagement.reviews.ReviewRepository;
import com.shakthi.jobmanagement.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;
    CompanyServiceImpl companiesService;

    //private static Long reviewId = 1L;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyServiceImpl companiesService) {
        this.reviewRepository = reviewRepository;
        this.companiesService = companiesService;
    }

    @Override
    public List<Review> getReviewByCompanyId(Long companyId) {
        Company company = companiesService.findCompanyById(companyId);
        if(company==null){
            return null;
        }
        return company.getReviewList();
    }

    @Override
    public Boolean addReviewByCompanyId(Review review, Long companyId) {


        Company company = companiesService.findCompanyById(companyId);
        Long reviewId = 0L;
        if(company==null){
            return false;
        }
        List<Review> reviews = company.getReviewList();
        if(reviews==null){
            reviewId = 1L;
        }
        else{
            for(Review reviewSingle : reviews){
                if(reviewSingle.getReviewId()>reviewId){
                    reviewId = reviewSingle.getReviewId();
                }
            }
        }
        review.setReviewId(++reviewId);
        review.setCompany(company);
        reviewRepository.save(review);
        reviews.add(review);
        company.setReviewList(reviews);
        companiesService.updateCompany(companyId,company);
        return true;

    }

    @Override
    public Review getReviewByReviewId(Long companyId, Long reviewId) {
        Company company = companiesService.findCompanyById(companyId);
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
        Company company = companiesService.findCompanyById(companyId);
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
        Company company = companiesService.findCompanyById(companyId);
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
