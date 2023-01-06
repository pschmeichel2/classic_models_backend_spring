package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Office;
import com.example.demo.repository.OfficeRepository;



@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class OfficeController {
    
    @Autowired
    OfficeRepository officeRepository;
    
    @GetMapping("/offices")
    public ResponseEntity<List<Office>> getAllOffices() {
      List<Office> offices = new ArrayList<Office>();
        officeRepository.findAll().forEach(offices::add);
      if (offices.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    
    @GetMapping("/offices/{officeCode}")
    public ResponseEntity<Office> getOfficeByOfficeNumber(@PathVariable("officeCode") String officeCode) {
      Office _office = officeRepository.findById(officeCode)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Office with id = " + officeCode+ " not found"));
    
      return new ResponseEntity<>(_office, HttpStatus.OK );  
    }

    @PutMapping("/offices/{officeCode}")
    public ResponseEntity<Office> updateOffice(@PathVariable("officeCode") String officeCode, @Valid @RequestBody Office office) {
      Office _office = officeRepository.findById(officeCode)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Office with officeCode = " + officeCode+ " not found"));

      _office.setCity(office.getCity());
      _office.setPhone(office.getPhone());
      _office.setAddressLine1(office.getAddressLine1());
      _office.setAddressLine2(office.getAddressLine2());
      _office.setState(office.getState());
      _office.setCountry(office.getCountry());
      _office.setPostalCode(office.getPostalCode());
      _office.setTerritory(office.getTerritory());
  
      return new ResponseEntity<>(officeRepository.save(_office), HttpStatus.OK ); 
    }

    @PostMapping("/offices")
    public ResponseEntity<Office> createCustomer(@Valid @RequestBody Office office) {   
      int officeCode = officeRepository.getNewOfficeCode();
      office.setOfficeCode(Integer.toString(officeCode));
      return new ResponseEntity<>(officeRepository.save(office), HttpStatus.OK ); 
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
      public ResourceNotFoundException(String message)   
      {  
      super(message);  
      }  
    }
    
    @DeleteMapping("/offices/{officeCode}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("officeCode") String officeCode) {
      try {
        officeRepository.deleteById(officeCode);
      } catch (EmptyResultDataAccessException ex) {
          throw new ResourceNotFoundException(String.format("Office with officeCode %s not found", officeCode));
      }
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
