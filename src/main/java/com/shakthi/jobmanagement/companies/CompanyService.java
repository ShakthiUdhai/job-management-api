package com.shakthi.jobmanagement.companies;

import java.util.List;

public interface CompanyService {

    List<Company> findall();
    boolean updateCompany(Long id, Company company);
    boolean addCompany(Company company);
    boolean deleteCompany(Long id);
    Company findCompanyById(Long id);

}
