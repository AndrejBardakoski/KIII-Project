package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.User;

import java.time.LocalDate;
import java.util.List;

public interface AuthService {

    User login(String username, String password);

    User register(String username, String password, String name, String surname, LocalDate dateOfBirth);

    List<User> findAll();

    User findById(Long userId);
}
