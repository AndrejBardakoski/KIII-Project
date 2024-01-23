package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "balloon_shop_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String balloonColor;
    private String balloonSize;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Order() {
    }

    public Order(String balloonColor, String balloonSize,User user) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.user = user;
    }
}
