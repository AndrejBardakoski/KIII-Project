package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;

import java.util.List;

public interface BalloonService {
    List<Balloon> listAll();
    List<Balloon> searchByNameOrDescription(String text);
    void deleteById(Long id);
    void save(Long id,String name,String description,Long manufacturerId);
    Balloon findById(Long id);
    Integer getNumDeletedBalloons();
    Integer updateNumDeletedBalloons();
}
