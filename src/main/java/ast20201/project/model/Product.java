package ast20201.project.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class Product {
    private long id;
    @NotBlank(message = "Product name is a required field")
    private String name;

    @NotNull(message = "Price is a required field")
    @Digits(integer = 10, fraction = 2, message = "Please input a valid price")
    private BigDecimal price;
    private String description;
    private List<Category> categories;
    private String image;

    public Product() {
        categories = new ArrayList<Category>();
    }

    public Product(long id, String name, BigDecimal price, String description, List<Category> categories, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categories = categories;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategory(List<Category> categories) {
        this.categories = categories;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
