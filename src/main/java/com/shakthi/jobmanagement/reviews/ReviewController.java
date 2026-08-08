package com.shakthi.jobmanagement.reviews;


import com.shakthi.jobmanagement.reviews.impl.ReviewServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    ReviewServiceImpl reviewService;

    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/companies/{companyId}/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        List<Review> reviews =  reviewService.getReviewByCompanyId(companyId);

        if(reviews==null){
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }

    @PostMapping("/companies/{companyId}/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        Boolean res = reviewService.addReviewByCompanyId(review,companyId);
        if(res){
            return new ResponseEntity<>("Review Added Successfully!",HttpStatus.OK);
        }

        return new ResponseEntity<>("Company with id : "+companyId+" not found!",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/companies/{companyId}/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
        Review review = reviewService.getReviewByReviewId(companyId,reviewId);
        if(review==null){
            return new ResponseEntity<>((HttpHeaders) null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review,HttpStatus.OK);
    }

    @PutMapping("/companies/{companyId}/reviews/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long companyId,@PathVariable Long reviewId, @RequestBody Review review){
        Boolean res = reviewService.updateReviewByReviewId(companyId,reviewId,review);
        if(res){
            return new ResponseEntity<>("Updated Successfully!",HttpStatus.OK);
        }

        return new ResponseEntity<>("Company with id : "+companyId+" not found!",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/companies/{companyId}/reviews/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
        Boolean res = reviewService.deleteReviewByReviewId(companyId,reviewId);
        if(res){
            return new ResponseEntity<>("Deleted Successfully!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Company/Review Not Found!",HttpStatus.NOT_FOUND);
    }


}
