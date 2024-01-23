package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private LocalDateTime dateCreated;
//    @OneToMany(mappedBy = "id")
    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orders;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.user = user;
        this.orders = new ArrayList<>();
    }

    public ShoppingCart(User user, LocalDateTime dateCreated) {
        this.user = user;
        this.dateCreated = dateCreated;
    }
}

