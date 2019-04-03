package ast20201.project.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class ProductFilter {
    private String name;
    private Long category;
    private BigDecimal priceLowerBound;
    private BigDecimal priceUpperBound;

    public ProductFilter() {
        this.name = null;
        this.category = null;
        this.priceLowerBound = null;
        this.priceUpperBound = null;
    }
    
    public ProductFilter(String name, Long category, BigDecimal priceLowerBound, BigDecimal priceUpperBound) {
        this.name = name;
        this.category = category;
        this.priceLowerBound = priceLowerBound;
        this.priceUpperBound = priceUpperBound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public BigDecimal getPriceLowerBound() {
        return priceLowerBound;
    }

    public void setPriceLowerBound(BigDecimal priceLowerBound) {
        this.priceLowerBound = priceLowerBound;
    }

    public BigDecimal getPriceUpperBound() {
        return priceUpperBound;
    }

    public void setPriceUpperBound(BigDecimal priceUpperBound) {
        this.priceUpperBound = priceUpperBound;
    }
}
