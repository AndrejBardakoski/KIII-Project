package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;

public interface OrderService{
    public Order placeOrder(String balloonColor, String balloonSize, User user);
    public Order findById(Long id);
}