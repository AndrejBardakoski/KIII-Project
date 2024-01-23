package mk.finki.ukim.mk.lab.repository.inMemory;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryManufacturerRepository {
    private List<Manufacturer> manufacturers;

    @PostConstruct
    void init(){
        manufacturers = new ArrayList<Manufacturer>();

        manufacturers.add(new Manufacturer("Balloons Inc","USA","NA SU"));
        manufacturers.add(new Manufacturer("Flying Dragon","China","BIJ CH 23"));
        manufacturers.add(new Manufacturer("AirBerlin","Germany","BRL DM 234"));
        manufacturers.add(new Manufacturer("BalloonRU","Russia","MSC PPL 156"));
        manufacturers.add(new Manufacturer("DelhiBalloon","India","ND MDA 9002"));
    }

    public List<Manufacturer> findAll(){ return manufacturers;}

    public Optional<Manufacturer> findById(Long manufacturerId) {
        return manufacturers.stream().filter(i -> i.getId().equals(manufacturerId)).findFirst();
    }
}
