package com.examly.springapp.service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.customExceptions.CartHasNoMobiles;
import com.examly.springapp.customExceptions.CartNotFoundException;
import com.examly.springapp.customExceptions.CartNotSavedException;
import com.examly.springapp.customExceptions.CommonException;
import com.examly.springapp.model.Cart;
import com.examly.springapp.model.Mobile;
import com.examly.springapp.repository.CartRepo;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private MobileService mobileService;



    @Override
    public Cart addCart(Cart cart){

        try{
        Cart cartObjectAfterSaving =  this.cartRepo.save(cart);

        if(cartObjectAfterSaving!=null){

            return cartObjectAfterSaving;

        }

        log.error("cart Object not saved exception"+cart.toString());
        throw new CartNotSavedException("cart not saved exception");

    }
    catch(Exception e){
            
        throw new CommonException("something error occured",e);

    }
    }

    @Override
    public Cart editCart(Long cartId, Cart updatedCart){

        try{
       Optional<Cart> ops = this.cartRepo.findById(cartId);
       Cart newCart = null;
       if(ops.isPresent()){
        newCart = ops.get();
        newCart.setCustomer(updatedCart.getCustomer());
        newCart.setMobiles(updatedCart.getMobiles());
        newCart.setTotalAmount(updatedCart.getTotalAmount());
     
            return this.cartRepo.save(newCart);
       }
        log.error("cart not found with given "+cartId);
        throw new CartNotFoundException("cart not found with given "+cartId);
       }
       catch(Exception e){

        throw new CommonException("something error occured",e);
       }

    }



    @Override
    public Cart removeMobileFromCart(Long cartId,Long mobileId){

        try{

          Cart  cartObjectByCartId  = this.cartRepo.findById(cartId).orElse(null);

           if(cartObjectByCartId!=null){

                Mobile mobileFromDataBase=  this.mobileService.getMobileById(mobileId);

                if(mobileFromDataBase!=null){

                List<Mobile> listOfMobileAfterDeletingByMobileId=cartObjectByCartId.getMobiles();

                for(int i=0;i<listOfMobileAfterDeletingByMobileId.size();i++){

                      if(listOfMobileAfterDeletingByMobileId.get(i).getMobileId()==mobileId){

                            listOfMobileAfterDeletingByMobileId.remove(i);
                            break;
                      }
                }
                  cartObjectByCartId.setMobiles(listOfMobileAfterDeletingByMobileId);
                   return this.addCart(cartObjectByCartId);
                }    
           }
           log.error("cart not found with given "+cartId);
           throw new CartNotFoundException("cart not found with given "+cartId);
        }
        catch(Exception e){

            throw new CommonException("something error occured",e);
        }
    }

    @Override
    public Cart removeAllMobiles(Long cartId){
        
        try{

        Optional<Cart> ops = cartRepo.findById(cartId);
        Cart newCart = null;
        List <Mobile> mobileList = null;
        if(ops.isPresent()){
         newCart = ops.get();
         newCart.setMobiles(null);
         cartRepo.save(newCart);
         return newCart;
        }
        log.error("cart not found with given "+cartId);
        throw new CartNotFoundException("cart not found with given "+cartId);
        }
        catch(Exception e){

            throw new CommonException("something error occured",e);
        }
        
    }

    @Override
    public Cart removeAllMobileByMobileId(Long cartId,Long mobileId){

        try{

        Cart  cartObjectByCartId  = this.cartRepo.findById(cartId).orElse(null);

        if(cartObjectByCartId!=null){

             Mobile mobileFromDataBase=  this.mobileService.getMobileById(mobileId);

             if(mobileFromDataBase!=null){

             List<Mobile> listOfMobileAfterDeletingByMobileId=cartObjectByCartId.getMobiles();

                listOfMobileAfterDeletingByMobileId.removeAll(Arrays.asList(mobileFromDataBase));

               cartObjectByCartId.setMobiles(listOfMobileAfterDeletingByMobileId);

                return this.addCart(cartObjectByCartId);
             }    
        }
        log.error("cart not found with given "+cartId);
        throw new CartNotFoundException("cart not found with given "+cartId);
        }
        catch(Exception e){

            throw new CommonException("something error occured",e);
        }           
    }

    @Override
    public Cart getCartByCustomerId(Long customerId){

        try{
           return cartRepo.findByCustomerId(customerId);
        }
        catch(Exception e){

            throw new CommonException("something error occured",e);
        }
    }

    @Override
    public Cart getCartByUserId(Long userId){
        try{
        return cartRepo.findByUserId(userId);
        }
        catch(Exception e){
            throw new CommonException("something error occured",e);
        }
    }

    @Override
    public List<Mobile> getMobilesFromCartUsingCartId(Long cartId){

        try{
         Cart cartObjectByCartId=  this.cartRepo.findById(cartId).orElse(null);

          if(cartObjectByCartId!=null){

                return cartObjectByCartId.getMobiles();
          }
           throw new CartHasNoMobiles("No mobiles present in the cart");
        }
        catch(Exception e){
            throw new CommonException("something error occured",e);
        }
    
    }
}
