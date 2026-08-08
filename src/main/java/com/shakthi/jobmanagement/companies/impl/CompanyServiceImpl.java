package com.shakthi.jobmanagement.companies.impl;

import com.shakthi.jobmanagement.companies.Company;
import com.shakthi.jobmanagement.companies.CompaniesRepository;
import com.shakthi.jobmanagement.companies.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompaniesRepository companiesRepository;
   // private Long idSeq = 1L;

    public CompanyServiceImpl(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @Override
    public List<Company> findall() {
        return companiesRepository.findAll();
    }

    @Override
    public boolean updateCompany(Long id, Company company) {

        Optional<Company> companyOptional = companiesRepository.findById(id);
            if(companyOptional.isPresent()) {
                Company companyToBeUpdated = companyOptional.get();
                companyToBeUpdated.setId(company.getId());
                companyToBeUpdated.setLocation(company.getLocation());
                companyToBeUpdated.setName(company.getName());
                //companyToBeUpdated.setReviewList(company.getReviewList());
                companiesRepository.save(companyToBeUpdated);
                return true;
            }
            return false;
    }

    @Override
    public boolean addCompany(Company company) {
        //company.setId(idSeq++);
        companiesRepository.save(company);
        return true;
    }

    @Override
    public boolean deleteCompany(Long id) {
        Optional<Company> isCompanyFound = companiesRepository.findById(id);
        if(isCompanyFound.isPresent()){
            companiesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company findCompanyById(Long id) {
        return companiesRepository.findById(id).orElse(null);
    }
}
