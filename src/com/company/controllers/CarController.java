package com.company.controllers;

import com.company.entities.Car;
import com.company.entities.Client;
import com.company.repositories.interfaces.ICarRepository;

import java.util.List;

public class CarController {
    private ICarRepository repository;

    public CarController(ICarRepository repository) {
        this.repository = repository;
    }

    public List<Car> showAllCars() {
        return repository.showAllCars();
    }

    public List<Car> showAllAvailableCars() {
        return repository.showAllAvailableCars();
    }

    public Car rentCar(int id, int clientID) {
        return repository.rentCar(id, clientID);
    }

    public Car addCar(Car car){
        return repository.addCar(car);
    }

    public List<Car> getMyCars(int clientID) {
        return repository.getMyCars(clientID);
    }

    public Car payment(int id, int clientID){
        return repository.payment(id, clientID);
    }
}
