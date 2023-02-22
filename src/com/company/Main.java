package com.company;

import com.company.controllers.CarController;
import com.company.controllers.ClientController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.CarRepository;
import com.company.repositories.ClientRepository;
import com.company.repositories.interfaces.ICarRepository;
import com.company.repositories.interfaces.IClientRepository;

public class Main {
    public static void main(String[] args) {
        IDB DB = new PostgresDB();
        IClientRepository repositoryClient = new ClientRepository(DB);
        ICarRepository repositoryCar = new CarRepository(DB);
        ClientController clientController = new ClientController(repositoryClient);
        CarController carController = new CarController(repositoryCar);
        MyApplication myApplication = new MyApplication(clientController, carController);
        myApplication.application();
    }

}
