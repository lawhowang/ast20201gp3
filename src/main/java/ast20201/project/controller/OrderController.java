package ast20201.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ast20201.project.exception.InsufficientCreditsException;
import ast20201.project.exception.InsufficientStockException;
import ast20201.project.model.FieldErrorResponse;
import ast20201.project.model.Order;
import ast20201.project.model.OrderProduct;
import ast20201.project.model.PageData;
import ast20201.project.model.User;
import ast20201.project.service.OrderService;
import ast20201.project.service.UserService;

@RequestMapping(value = "/api/order")
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getOrders(@RequestParam("page") int page) {
        User user = userService.getCurrentUser();
        PageData<Order> orders = orderService.getOrders(user.getId(), page);
        return ResponseEntity.ok(orders);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<?> getOrder(@PathVariable("orderId") long orderId) {
        User user = userService.getCurrentUser();
        Order order = orderService.getOrder(user.getId(), orderId);
        return ResponseEntity.ok(order);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        User user = userService.getCurrentUser();
        try {
            Order dborder = orderService.createOrder(user.getId(), order);
            return ResponseEntity.ok(dborder);
        } catch (InsufficientStockException ex) {
            FieldErrorResponse errors = new FieldErrorResponse();
            errors.addErrors("" + ex.getProduct().getId(), ex.getMessage());
            return ResponseEntity.badRequest().body(errors);
        }
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> cancelOrder(@PathVariable("orderId") long orderId) {
        User user = userService.getCurrentUser();
        orderService.cancelOrder(user.getId(), orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity<?> confirmOrder(@PathVariable("orderId") long orderId) {
        User user = userService.getCurrentUser();
        try {
            orderService.confirmOrder(user.getId(), orderId);
        } catch (InsufficientCreditsException ex) {
            FieldErrorResponse errors = new FieldErrorResponse();
            errors.addErrors("credits", ex.getMessage());
            return ResponseEntity.badRequest().body(errors);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}