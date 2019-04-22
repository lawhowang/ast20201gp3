package ast20201.project.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ast20201.project.model.Category;
import ast20201.project.model.PageData;
import ast20201.project.model.Product;
import ast20201.project.model.ProductFilter;
import ast20201.project.repository.CategoryRepository;
import ast20201.project.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public int getProductCount() {
        return productRepository.getProductCount();
    }

    public PageData<Product> getProducts(int page, ProductFilter filter, String sortBy) {
        return productRepository.getProducts(page, filter, sortBy);
    }

	public Product getProduct(long id) {
		return productRepository.getProduct(id);
    }
    public void updateProduct(long id, String name, BigDecimal price, Integer quantity, String description, List<Category> categories) {
        productRepository.updateProduct(id, name, price, quantity, description, categories);
    }

	public void deleteProduct(long id) {
        productRepository.deleteProduct(id);
	}

	public long addProduct(String name, BigDecimal price, Integer quantity, String description, List<Category> categories) {
        return productRepository.addProduct(name, price, quantity, description, categories);
    }
    
    public void updateProductImage(long id, String image) {
        productRepository.updateProductImage(id, image);
    }

	public List<Product> getLatestProducts() {
		return productRepository.getLatestProducts();
	}
}
