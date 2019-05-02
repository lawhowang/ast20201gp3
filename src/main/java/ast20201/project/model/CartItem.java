/*
 * Cart item model.
 */

package ast20201.project.model;

import org.springframework.stereotype.Component;

@Component
public class CartItem {
    private long productId;
    private int amount;

    public CartItem() {
        
    }

    public CartItem(long productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }
    
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
