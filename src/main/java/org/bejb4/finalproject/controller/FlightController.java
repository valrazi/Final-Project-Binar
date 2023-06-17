package org.bejb4.finalproject.controller;

import org.bejb4.finalproject.model.Flight;
import org.bejb4.finalproject.service.FlightService;
import org.bejb4.finalproject.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    FlightService flightService;
    @GetMapping
    public ResponseEntity<Object> getAllFlight(){
        return ResponseHandler.generateResponse("success", HttpStatus.OK, flightService.getAllFlight());
    }

    @PostMapping
    public ResponseEntity<Object> addNewFlight(@RequestBody Flight flight){
        try{
            return ResponseHandler.generateResponse("success", HttpStatus.CREATED, flightService.addNewFlight(flight));
        }catch(Exception e){
            return ResponseHandler.generateResponse("error", HttpStatus.BAD_REQUEST, null);
        }
    }
}
