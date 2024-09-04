package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.ConstantVariableClass;
import com.examly.springapp.model.Mobile;
import com.examly.springapp.service.MobileService;

@RestController
@RequestMapping("/api/mobile")
@CrossOrigin(origins = ConstantVariableClass.FRONTEND_URL,allowedHeaders="*")
public class MobileController {

    
    private MobileService mobileService;

    @Autowired
    public MobileController(MobileService mobileService){

         this.mobileService=mobileService;
    }

    @PostMapping
    public ResponseEntity<Mobile> addMobile(@RequestBody Mobile mobile){

        System.out.println("called");

                                                             
        return new ResponseEntity<>(this.mobileService.addMobile(mobile),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Mobile>> getAllMobile (){
        return new ResponseEntity<>(this.mobileService.getAllMobile(),HttpStatus.OK);
    }

    @PutMapping("/{mobileId}")
    public ResponseEntity<Mobile> updateMobile(@PathVariable("mobileId") Long mobileId,@RequestBody Mobile mobile){
        return new ResponseEntity<>(this.mobileService.editMobile(mobileId, mobile),HttpStatus.OK);
    }
    
    @DeleteMapping("/{mobileId}")
    public ResponseEntity<Mobile> deleteMobile(@PathVariable("mobileId") Long mobileId){
        return new ResponseEntity<>(this.mobileService.deleteMobile(mobileId),HttpStatus.OK);
    }

    @GetMapping("/{mobileId}")
    public ResponseEntity<Mobile>  getMobileById(@PathVariable("mobileId") Long mobileId){

          
            return new ResponseEntity<>(this.mobileService.getMobileById(mobileId),HttpStatus.OK);
    }



}
