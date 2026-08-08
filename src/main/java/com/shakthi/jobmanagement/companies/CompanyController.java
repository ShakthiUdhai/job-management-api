package com.shakthi.jobmanagement.companies;


import com.shakthi.jobmanagement.companies.impl.CompanyServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    private CompanyServiceImpl companiesService;

    public CompanyController(CompanyServiceImpl companiesService) {
        this.companiesService = companiesService;
    }

    @GetMapping("/companies/get")
    public ResponseEntity<List<Company>> getCompanies(){
        return new ResponseEntity<>(companiesService.findall(), HttpStatus.OK);
    }

    @PutMapping("/companies/update/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company){
        boolean response = companiesService.updateCompany(id,company);
        if(response){
            return new ResponseEntity<>("Company Updated Successfully!",HttpStatus.OK);
        }

        return new ResponseEntity<>("Company with id : "+id+" Not found",HttpStatus.NOT_FOUND);
    }

    @PostMapping("companies/add")
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        boolean response = companiesService.addCompany(company);
        if(response){
            return new ResponseEntity<>("Company Added Successfully!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("companies/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean response = companiesService.deleteCompany(id);
        if(response){
            return new ResponseEntity<>("Company deleted Succsessfully!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Company with id : "+id+" not found!",HttpStatus.NOT_FOUND);
    }

    @GetMapping("companies/get/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companiesService.findCompanyById(id);
        if(company==null){
            return new ResponseEntity<>((HttpHeaders) null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company,HttpStatus.OK);
    }

}
