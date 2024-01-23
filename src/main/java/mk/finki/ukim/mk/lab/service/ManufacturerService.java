package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    public List<Manufacturer> findAll();
    public void save(String name, String country, String address);
}
