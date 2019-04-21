/*
 * Cart model.
 */

package ast20201.project.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Cart {
    private List<CartItem> cartItems;
    private BigDecimal totalPrice;

    public Cart() {
        cartItems = new ArrayList<CartItem>();
        totalPrice = BigDecimal.ZERO;
    }

    public Cart(List<CartItem> cartItems, BigDecimal totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }
    
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
