package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;

import java.util.List;

public interface ShoppingCartService {
    public ShoppingCart createShoppingCart(User user);
    List<Order> listAllOrdersInShoppingCart(Long cartId);
    ShoppingCart addOrderToShoppingCart(Long cartId, Long orderId);

}
