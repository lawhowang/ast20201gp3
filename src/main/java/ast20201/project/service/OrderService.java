package ast20201.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ast20201.project.exception.InsufficientStockException;
import ast20201.project.model.FieldErrorResponse;
import ast20201.project.model.Order;
import ast20201.project.model.OrderProduct;
import ast20201.project.model.PageData;
import ast20201.project.model.Product;
import ast20201.project.repository.OrderRepository;
import ast20201.project.repository.ProductRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public PageData<Order> getOrders(String query, int page) {
        return orderRepository.getOrders(query, page);
    }

	public Order getOrder(long id) {
		return orderRepository.getOrder(id);
	}

    public Order getOrder(long userId, long orderId) {
        return orderRepository.getOrder(userId, orderId);
    }

    public PageData<Order> getOrders(long userId, int page) {
        return orderRepository.getOrders(userId, page);
    }

    public Order createOrder(long userId, Order order) throws InsufficientStockException {
        if (order.getOrderProducts().size() == 0)
            return null;
        List<OrderProduct> orderProducts = order.getOrderProducts();
        for (OrderProduct orderProduct : orderProducts) {
            long product = orderProduct.getId();
            Product dbproduct = productRepository.getProduct(product);
            if (dbproduct.getQuantity() != null && orderProduct.getAmount() > dbproduct.getQuantity()) {
                throw new InsufficientStockException(
                        "Insufficient stock! Please adjust the amount below the stock level", dbproduct);
            }
        }
        String message = order.getMessage();
        Order dborder = orderRepository.createOrder(userId, orderProducts, message);
        return dborder;
    }

    public void cancelOrder(long userId, long orderId) {
        orderRepository.cancelOrder(userId, orderId);
    }

	public Order updateOrder(long id, Order order) {
        orderRepository.updateOrder(id, order.getOrderProducts(), order.getOrderStatus(), order.getPaymentStatus(), order.getCreateDate(), order.getMessage(), order.getAdminMessage());
        return getOrder(id);
	}

}
