package ast20201.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ast20201.project.model.Cart;
import ast20201.project.model.User;
import ast20201.project.service.CartService;
import ast20201.project.service.UserService;

@RequestMapping(value = "/api/cart")
@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getCart() {
        User user = userService.getCurrentUser();
        Cart cart = cartService.getCart(user.getId());
        return ResponseEntity.ok(cart);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> addItem(@PathVariable("productId") long productId, @RequestParam("amount") int amount) {
        User user = userService.getCurrentUser();
        cartService.addItem(user.getId(), productId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateItemAmount(@PathVariable("productId") long productId, @RequestParam("amount") int amount) {
        User user = userService.getCurrentUser();
        cartService.updateItemAmount(user.getId(), productId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeItem(@PathVariable("productId") long productId) {
        User user = userService.getCurrentUser();
        cartService.removeItem(user.getId(), productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> clear() {
        User user = userService.getCurrentUser();
        cartService.clear(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}