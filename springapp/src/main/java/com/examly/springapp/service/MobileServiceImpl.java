package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.customExceptions.AddMobileException;
import com.examly.springapp.customExceptions.CommonException;
import com.examly.springapp.customExceptions.GetMobilesException;
import com.examly.springapp.customExceptions.IfMobileNotDeletedException;
import com.examly.springapp.customExceptions.MobileNotFoundException;
import com.examly.springapp.model.Mobile;
import com.examly.springapp.repository.MobileRepo;

@Service
public class MobileServiceImpl implements MobileService{

    @Autowired
    private MobileRepo mobileRepo;

    @Override
    public Mobile addMobile(Mobile mobile){
    
            try{

                Mobile  mobileObjectAfterSaving = mobileRepo.save(mobile);

                    return mobileObjectAfterSaving;
            }
            catch(Exception e){

                throw new AddMobileException("Mobile cannot be added",e);
            }
      
    }

    @Override
    public Mobile getMobileById(Long mobileId){

          try{

            Mobile   mobileFromDataBase =this.mobileRepo.findById(mobileId).orElse(null);

            if(mobileFromDataBase!=null){

                 return mobileFromDataBase;
            }
            else{
                throw new MobileNotFoundException("Mobile not found with the given ID");
            }
        
        }
        catch(Exception e){
                     
               throw new CommonException("something error occured",e);
        }
    }

        @Override
        public List<Mobile> getAllMobile(){

            
        try{
          List<Mobile> listOfMobiles=mobileRepo.findAll();
          
           if(listOfMobiles.isEmpty()){

            throw new GetMobilesException("Mobile List is Empty");
            }
             return listOfMobiles;
        }
        catch(Exception e){

            throw new CommonException("something error occured",e);
        }
    }
    
    @Override
    public Mobile editMobile(Long mobileId,Mobile updatedMobile){

        try{
        Mobile existingMobile=this.mobileRepo.findById(mobileId).orElse(null);
        if(existingMobile!=null){
            existingMobile.setModel(updatedMobile.getModel());
            existingMobile.setBrand(updatedMobile.getBrand());
            existingMobile.setImageUrl(updatedMobile.getImageUrl());
            existingMobile.setDescription(updatedMobile.getDescription());
            existingMobile.setQuantity(updatedMobile.getQuantity());
            existingMobile.setPrice(updatedMobile.getPrice());
            return mobileRepo.save(existingMobile);
        }
        
        else{
            throw new MobileNotFoundException("Mobile not found with the given ID");
        }
    }
    catch(Exception e){
        throw new CommonException("something error occured",e);
    }
    }
    
    @Override
    public Mobile deleteMobile(Long mobileId){

        try{
        Mobile existingMobile=this.mobileRepo.findById(mobileId).orElse(null);
        
        if(existingMobile!=null){
            mobileRepo.deleteById(mobileId);

            Mobile    mobileObjectAfterDeleting=this.getMobileById(mobileId);
            
            if(mobileObjectAfterDeleting!=null){
                
                throw new IfMobileNotDeletedException("Mobile is not deleted");
            }
            return existingMobile;
        }
        else{
            throw new MobileNotFoundException("Mobile not found with the given ID");
        }
        
    }
    catch(Exception e){

        throw new CommonException("something error occured",e);
    }
}
}
