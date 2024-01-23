package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.exceptions.BalloonNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.repository.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.ManufacturerRepository;
import mk.finki.ukim.mk.lab.repository.inMemory.InMemoryBalloonRepository;
import mk.finki.ukim.mk.lab.repository.inMemory.InMemoryManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalloonServiceImpl implements BalloonService {
    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByNameLike("%" + text);
    }

    @Override
    public void deleteById(Long id) {
        if (balloonRepository.findById(id).isPresent())
            this.balloonRepository.deleteById(id);
    }

    @Override
    public void save(Long id, String name, String description, Long manufacturerId) {
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        Balloon balloon = balloonRepository.findById(id).orElse(null);
        if (balloon == null) {
            balloonRepository.save(new Balloon(name, description, manufacturer));
        } else {
            balloon.setName(name);
            balloon.setDescription(description);
            balloon.setManufacturer(manufacturer);
            balloonRepository.save(balloon);
        }
    }

    @Override
    public Balloon findById(Long id) {
        return balloonRepository.findById(id).orElseThrow(() -> new BalloonNotFoundException(id));
    }

    @Override
    public Integer getNumDeletedBalloons() {
//        return balloonRepository.getNumDeletedBalloons();
        return null;
    }

    @Override
    public Integer updateNumDeletedBalloons() {
//        return balloonRepository.UpdateNumDeletedBalloons();
        return null;
    }
}
