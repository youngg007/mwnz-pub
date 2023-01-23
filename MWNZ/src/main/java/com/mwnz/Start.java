package com.mwnz;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mwnz.business.Company;
import com.mwnz.util.MWNZ;

@RestController
@EnableAutoConfiguration
public class Start {

    @RequestMapping("company/v1/{id}")
    public ResponseEntity<Company> company(@PathVariable("id") String id) {
        
        System.out.println("\nMWNZ companies web service call... path parameter = " + id);
        
        // call MWNZ for company data
        MWNZ mwnz = MWNZ.getInstance();
        Company company = mwnz.getCompany(id);
        
        if (company == null){
            
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        }
        
        else{
            
            // JSON is the default content type (i am lazy... ha ha)
            return ResponseEntity.ok(company);
        }
    }

    public static void main(String[] args) throws Exception {
        
        SpringApplication.run(Start.class, args);
    }
}