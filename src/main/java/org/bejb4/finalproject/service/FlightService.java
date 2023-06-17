package org.bejb4.finalproject.service;

import org.bejb4.finalproject.model.Flight;
import org.bejb4.finalproject.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    public List<Flight> getAllFlight(){
        return flightRepository.findAll();
    }

    public Flight addNewFlight(Flight flight){
        return flightRepository.save(flight);
    }
}
