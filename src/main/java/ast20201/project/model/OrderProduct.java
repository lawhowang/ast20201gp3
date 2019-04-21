/*
 * Order product model.
 */

package ast20201.project.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class OrderProduct {
    private long id;
    private int amount;
    private BigDecimal price;

    public OrderProduct() {
        
    }

    public OrderProduct(long id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public OrderProduct(long id, int amount, BigDecimal price) {
        this.id = id;
        this.amount = amount;
        this.price = price;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
