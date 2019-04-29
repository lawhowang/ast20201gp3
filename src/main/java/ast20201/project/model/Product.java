package ast20201.project.model;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Product {
    private long id;

    @NotBlank(message = "Product name is a required field")
    private String name;

    @NotNull(message = "Price is a required field")
    @Digits(integer = 10, fraction = 2, message = "Please input a valid price")
    private BigDecimal price;

    @Nullable
    @Min(value = 0L, message = "Quantity must be a positive number")
    private Integer quantity;

    private String description;
    private List<Category> categories;
    private Blob image;

    public Product() {
        categories = new ArrayList<Category>();
    }

    public Product(long id, String name, BigDecimal price, Integer quantity, String description, List<Category> categories, Blob image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
    
    @JsonIgnore
    public Blob getImage() {
        return this.image;
    }
    
    @JsonProperty("image")
    public String getImageBase64() throws SQLException {
    	if (null == image) return null;
      return "data:image/jpeg;base64," + new String(Base64.getEncoder().encode(image.getBytes(1, (int) image.length())));
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
