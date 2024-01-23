package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BalloonRepository extends JpaRepository<Balloon, Long> {
    List<Balloon> findAllByNameLike(String name);
}
