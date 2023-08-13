package com.project.cities.controller;

import com.project.cities.exception.CityAlreadyExistException;
import com.project.cities.exception.CityNotFoundException;
import com.project.cities.model.City;
import com.project.cities.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> getCities(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(cityService.getCities(name), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable String id) {
        return new ResponseEntity<>(getCityById(id), OK);
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City newCity) {
        return new ResponseEntity<>(cityService.createCity(newCity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCity(@PathVariable String id, @RequestBody City newCity) {
        cityService.updateById(id, newCity);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCityById(@PathVariable String id) {
        cityService.deleteById(id);
        return new ResponseEntity<>(OK);
    }

    private City getCityById(String id) {
       return cityService.getCityById(id);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<String> handleCityNotFoundEx(CityNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(CityAlreadyExistException.class)
    public ResponseEntity<String> handleCityAlreadyExistEx(CityAlreadyExistException ex){
        return new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }
}
