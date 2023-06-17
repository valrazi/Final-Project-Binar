package org.bejb4.finalproject.controller;

import org.bejb4.finalproject.dto.CityDTO;
import org.bejb4.finalproject.model.City;
import org.bejb4.finalproject.service.CityService;
import org.bejb4.finalproject.utils.ResponseHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/city")
public class CityController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<Object> getAllCity(){
        List<CityDTO> cityList = cityService.getAllCity().stream().map(city -> modelMapper.map(city, CityDTO.class)).collect(Collectors.toList());
        return ResponseHandler.generateResponse("success", HttpStatus.OK, cityList);
    }

    @PostMapping
    public ResponseEntity<Object> createCity(@RequestBody CityDTO cityDTO) {
        City cityRequest = modelMapper.map(cityDTO, City.class);

        City city = cityService.createCity(cityRequest);

        CityDTO cityResponse = modelMapper.map(city, CityDTO.class);

        return ResponseHandler.generateResponse("success", HttpStatus.CREATED, cityResponse);
    }

}
