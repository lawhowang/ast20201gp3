package ast20201.project.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ast20201.project.model.Cart;
import ast20201.project.model.CartItem;
import ast20201.project.repository.CartRepository;
import ast20201.project.repository.ProductRepository;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepositry;

    public Cart getCart(long userId) {
        List<CartItem> cartItems = getItems(userId);
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            BigDecimal price = productRepositry.getProduct(cartItem.getProduct()).getPrice();
            totalPrice.add(price);
        }
        return new Cart(cartItems, totalPrice);
    }

    public List<CartItem> getItems(long userId) {
        List<CartItem> cartItems = cartRepository.getItems(userId);
        return cartItems;
    }

    public void addItem(long userId, long productId, int amount) {
        int q = cartRepository.getItemAmount(userId, productId);
        if (q == 0)
            cartRepository.addItem(userId, productId, amount);
        else
            cartRepository.updateItemAmount(userId, productId, q + amount);
    }

    public void addItem(long userId, long productId) {
        cartRepository.addItem(userId, productId, 1);
    }

    public void updateItemAmount(long userId, long productId, int amount) {
        if (amount == 0)
            cartRepository.removeItem(userId, productId);
        else
            cartRepository.updateItemAmount(userId, productId, amount);
    }

    public void removeItem(long userId, long productId) {
        cartRepository.removeItem(userId, productId);
    }

	public void clear(long userId) {
        cartRepository.clear(userId);
	}
}
