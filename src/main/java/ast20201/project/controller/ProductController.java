package ast20201.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ast20201.project.model.Product;
import ast20201.project.service.ProductService;

@RequestMapping(value = "/api")
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getProduct(@PathVariable("id") long id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }
}