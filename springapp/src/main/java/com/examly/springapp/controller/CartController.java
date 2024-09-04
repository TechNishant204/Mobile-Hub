package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Cart;
import com.examly.springapp.model.ConstantVariableClass;
import com.examly.springapp.model.Mobile;
import com.examly.springapp.service.CartService;
import com.examly.springapp.service.CartServiceImpl;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = ConstantVariableClass.FRONTEND_URL,allowedHeaders="*")
@RequestMapping("/api/cart")
public class CartController {


    private CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService=cartService;
    }

    @PostMapping
    public ResponseEntity<?> addCart(@RequestBody Cart cart){
        Cart c = cartService.addCart(cart);
        
            return ResponseEntity.status(201).body(c);
       
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<?> editCart(@PathVariable Long cartId,@RequestBody Cart cart){

        Cart c = cartService.editCart(cartId, cart);
        
        System.out.println("cart obj from angular ***************"+cart);
            return ResponseEntity.status(200).body(c);
       
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<?> viewCartByUserId(@PathVariable Long userId){
        Cart c = cartService.getCartByUserId(userId);
        if(c!=null){
            return ResponseEntity.status(200).body(c);
        }else{
            return ResponseEntity.status(404).body(c);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCartByCustomerId(@PathVariable Long customerId){
         Cart c = cartService.getCartByCustomerId(customerId);
        if(c!=null){
            return ResponseEntity.status(200).body(c);
        }else{
            return ResponseEntity.status(404).body("no cart found");
        }
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<Mobile>> getMobilesFromCartUsingCartId(@PathVariable Long cartId){

            return new ResponseEntity<>(this.cartService.getMobilesFromCartUsingCartId(cartId),HttpStatus.OK);
    } 

    @DeleteMapping("/{cartId}/mobile/{mobileId}")
    public ResponseEntity<?> removeMobileFromCart(@PathVariable Long cartId,@PathVariable Long mobileId){
        Cart c = cartService.removeMobileFromCart(cartId, mobileId);
        if(c!=null){
            return ResponseEntity.status(200).body(c);
        }else{
            return ResponseEntity.status(404).body(c);
        }
    }

    @DeleteMapping("/removemobile/{cartId}/{mobileId}")
    public ResponseEntity<Cart> removeAllMobileByMobileId(@PathVariable Long cartId,@PathVariable Long mobileId){

           Cart cartObject= this.cartService.removeAllMobileByMobileId(cartId,mobileId);

        return new ResponseEntity<>(cartObject,HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> removeAllMobile(@PathVariable Long cartId){
        Cart c = cartService.removeAllMobiles(cartId);
       
            return new ResponseEntity<>(c,HttpStatus.OK);
       
    }
}
