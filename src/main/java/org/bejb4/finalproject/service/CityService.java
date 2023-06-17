package org.bejb4.finalproject.service;

import org.bejb4.finalproject.model.City;
import org.bejb4.finalproject.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    public City createCity(City city){
        return cityRepository.save(city);
    }
}
