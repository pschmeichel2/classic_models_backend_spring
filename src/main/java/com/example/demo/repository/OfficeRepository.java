package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Office;

public interface OfficeRepository extends JpaRepository<Office, String> {

    @Query("select max(convert(o.officeCode, unsigned))+1 from Office o") // this is JPQL so use classnames
    int getNewOfficeCode();

}
