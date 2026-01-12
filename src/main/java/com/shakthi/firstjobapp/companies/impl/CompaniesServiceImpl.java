package com.shakthi.firstjobapp.companies.impl;

import com.shakthi.firstjobapp.companies.Companies;
import com.shakthi.firstjobapp.companies.CompaniesRepository;
import com.shakthi.firstjobapp.companies.CompaniesService;
import com.shakthi.firstjobapp.reviews.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniesServiceImpl implements CompaniesService {

    private CompaniesRepository companiesRepository;
    private Long idSeq = 1L;

    public CompaniesServiceImpl(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @Override
    public List<Companies> findall() {
        return companiesRepository.findAll();
    }

    @Override
    public boolean updateCompany(Long id,Companies company) {

        Optional<Companies> companyOptional = companiesRepository.findById(id);
            if(companyOptional.isPresent()) {
                Companies companyToBeUpdated = companyOptional.get();
                companyToBeUpdated.setId(company.getId());
                companyToBeUpdated.setLocation(company.getLocation());
                companyToBeUpdated.setName(company.getName());
                //companyToBeUpdated.setReviewList(company.getReviewList());
                List<Review> reviews = company.getReviewList();
                if(!reviews.isEmpty()){
                    companyToBeUpdated.setReviewList(reviews);
                }
                companiesRepository.save(companyToBeUpdated);
                return true;
            }
            return false;
    }

    @Override
    public boolean addCompany(Companies company) {
        company.setId(idSeq++);
        companiesRepository.save(company);
        return true;
    }

    @Override
    public boolean deleteCompany(Long id) {
        Optional<Companies> isCompanyFound = companiesRepository.findById(id);
        if(isCompanyFound.isPresent()){
            companiesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Companies findCompanyById(Long id) {
        return companiesRepository.findById(id).orElse(null);
    }
}
