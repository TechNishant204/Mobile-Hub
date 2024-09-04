package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Cart;
import com.examly.springapp.model.Mobile;

public interface CartService {

    public Cart addCart(Cart cart);

    public Cart editCart(Long cartId,Cart updatedCart);

    public Cart removeMobileFromCart(Long cartId,Long mobileId);
    
    public Cart removeAllMobiles(Long cartId);

    public Cart getCartByUserId(Long userId);

    public Cart getCartByCustomerId(Long customerId);

    public List<Mobile>  getMobilesFromCartUsingCartId(Long cartId);

    public Cart removeAllMobileByMobileId(Long cartId,Long mobileId);
}
