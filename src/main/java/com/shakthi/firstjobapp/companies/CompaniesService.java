package com.shakthi.firstjobapp.companies;

import java.util.List;

public interface CompaniesService {

    List<Companies> findall();
    boolean updateCompany(Long id,Companies company);
    boolean addCompany(Companies company);
    boolean deleteCompany(Long id);
    Companies findCompanyById(Long id);

}
