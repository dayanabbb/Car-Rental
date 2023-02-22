package com.company.repositories.interfaces;

import com.company.entities.Car;
import com.company.entities.Client;

import java.util.List;

public interface ICarRepository {

    List<Car> showAllCars();
    List<Car> showAllAvailableCars();
    Car rentCar(int id, int clientID);

    Car addCar(Car car);

    List<Car>  getMyCars(int clientID);

    Car payment(int id, int clientID);
}
