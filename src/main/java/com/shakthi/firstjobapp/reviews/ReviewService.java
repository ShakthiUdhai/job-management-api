package com.shakthi.firstjobapp.reviews;

import java.util.List;

public interface ReviewService {

    List<Review> getReviewByCompanyId(Long companyId);
    Boolean addReviewByCompanyId(Review review,Long companyId);
    Review getReviewByReviewId(Long companyId,Long reviewId);
    Boolean updateReviewByReviewId(Long companyId,Long reviewId,Review review);
    Boolean deleteReviewByReviewId(Long companyId,Long reviewId);

}
