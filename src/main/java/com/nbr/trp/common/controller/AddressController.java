package com.nbr.trp.common.controller;


import com.nbr.trp.common.entity.Address;
import com.nbr.trp.common.repository.AddressRepository;
import com.nbr.trp.common.service.AddressService;
import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.user.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody Address address) {
        try{
            Address address1 = addressService.saveAddress(address);
            // return ResponseEntity.ok(new MessageResponse("Representative registered successfully!"));
            return new ResponseEntity<>("Address added", HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
