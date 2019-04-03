package ast20201.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ast20201.project.model.PageData;
import ast20201.project.model.Product;
import ast20201.project.model.ProductFilter;
import ast20201.project.service.ProductService;

@RequestMapping(value = "/api")
@Controller
public class SearchController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/search/{category}/{name}/{page}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getProduct(@PathVariable("category") long category,
            @PathVariable("name") String name, @PathVariable(value = "page") int page) {
        ProductFilter filter = new ProductFilter();
        if (category != 0)
            filter.setCategory(category);
        filter.setName(name);
        PageData<Product> products = productService.getProducts(page, filter, null);
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/search/{category}/{name}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getProduct(@PathVariable("category") long category,
            @PathVariable("name") String name) {
        ProductFilter filter = new ProductFilter();
        if (category != 0)
            filter.setCategory(category);
        filter.setName(name);
        PageData<Product> products = productService.getProducts(1, filter, null);
        return ResponseEntity.ok(products);
    }
}