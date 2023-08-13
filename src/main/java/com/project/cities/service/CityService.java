package com.project.cities.service;

import com.project.cities.exception.CityAlreadyExistException;
import com.project.cities.exception.CityNotFoundException;
import com.project.cities.model.City;
import com.project.cities.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getCities(String name) {
        if (name == null)
            return cityRepository.findAll();
        else {
            return cityRepository.findAllByName(name);
        }
    }

    public City createCity(City newCity) {
        Optional<City> cityByName = cityRepository.findByName(newCity.getName());
        if (cityByName.isPresent())
            throw new CityAlreadyExistException("City already exist name: " + newCity.getName());

        return cityRepository.save(newCity);
    }

    public void deleteById(String id) {
        cityRepository.deleteById(id);
    }

    public City getCityById(String id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found id: " + id));
    }

    public void updateById(String id, City newCity) {
        City old = getCityById(id);
        old.setName(newCity.getName());
        old.setCode(newCity.getCode());
        cityRepository.save(old);
    }
}
