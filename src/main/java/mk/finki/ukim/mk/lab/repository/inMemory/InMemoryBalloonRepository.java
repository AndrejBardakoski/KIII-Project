package mk.finki.ukim.mk.lab.repository.inMemory;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.exceptions.BalloonNotFoundException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryBalloonRepository {
    private List<Balloon> balloons;
    Integer numDeletedBalloons;

    @PostConstruct
    void init() {
        numDeletedBalloons=0;
        balloons = new ArrayList<Balloon>();

        Manufacturer tempMan1 = new Manufacturer("Balloons Inc", "USA", "NA SU");
        Manufacturer tempMan2 = new Manufacturer("Flying Dragon", "China", "BIJ CH 23");
        balloons.add(new Balloon("Black", "Black balloon", tempMan2));
        balloons.add(new Balloon("Red", "Red balloon", tempMan2));
        balloons.add(new Balloon("Green", "Green balloon", tempMan1));
        balloons.add(new Balloon("Blue", "Blue balloon", tempMan2));
        balloons.add(new Balloon("Yellow", "Yellow balloon", tempMan1));
        balloons.add(new Balloon("Orange", "Orange balloon", tempMan2));
        balloons.add(new Balloon("Purple", "Purple balloon", tempMan2));
        balloons.add(new Balloon("Pink", "Pink balloon", tempMan2));
        balloons.add(new Balloon("Cyan", "Cyan balloon", tempMan1));
        balloons.add(new Balloon("White", "White balloon", tempMan2));
    }

    public List<Balloon> findAllBalloons() {
        return balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return balloons.stream().filter(balloon -> balloon.getName().contains(text)
                || balloon.getDescription().contains(text)).collect(Collectors.toList());
    }

    public Balloon findById(Long id) {
        return balloons.stream().filter(i -> i.getId().equals(id)).findFirst().orElseThrow(() -> new BalloonNotFoundException(id));
    }

    public void deleteById(Long id) {
        balloons.removeIf(i -> i.getId().equals(id));
    }

    public void save(Long id, String name, String description, Manufacturer manufacturer) {
        Balloon balloon = balloons.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
        if (balloon == null) {
            balloons.add(new Balloon(name, description, manufacturer));
        } else {
            balloon.setName(name);
            balloon.setDescription(description);
            balloon.setManufacturer(manufacturer);
        }
    }
    public Integer getNumDeletedBalloons(){
        return numDeletedBalloons;
    }
    public Integer UpdateNumDeletedBalloons(){
        return ++numDeletedBalloons;
    }
}
