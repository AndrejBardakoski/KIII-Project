package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final OrderService orderService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, OrderService orderService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @Override
    public ShoppingCart createShoppingCart(User user) {
        ShoppingCart cart = new ShoppingCart(user);
        return this.shoppingCartRepository.save(cart);
    }

    @Override
    public List<Order> listAllOrdersInShoppingCart(Long cartId) {
        return this.shoppingCartRepository.findById(cartId).get().getOrders();
    }

    @Override
    public ShoppingCart addOrderToShoppingCart(Long cartId, Long orderId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId).orElse(null);
        Order order = orderService.findById(orderId);
//        if (shoppingCart.getOrders()
//                .stream().filter(i -> i.getId().equals(orderId))
//                .collect(Collectors.toList()).size() > 0)
//            throw new ProductAlreadyInShoppingCartException(productId, username);
        shoppingCart.getOrders().add(order);
        return this.shoppingCartRepository.save(shoppingCart);

    }
}
