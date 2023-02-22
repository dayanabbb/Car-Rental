//добавить проверку пароля на >8
// изменить данные аккаунта или пароль
// вернуть машину(удалить owner_id) и деньги(мб штраф)
// таблица для карт

package com.company;

import java.util.concurrent.TimeUnit;

import com.company.controllers.CarController;
import com.company.controllers.ClientController;
import com.company.entities.Car;
import com.company.entities.Client;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MyApplication {
    private ClientController clientController;
    private CarController carController;
    private Scanner scan;

    public MyApplication(ClientController clientController, CarController carController) {
        this.clientController = clientController;
        this.carController = carController;
        scan = new Scanner(System.in);
    }

    public void application() {
        try {
            Client client = new Client();
            System.out.println("Welcome to the ROD");
            System.out.println("1.Sign in\n2.Sign up");
            int answer = scan.nextInt();
            if (answer == 1) {
                client = sign_in();
                if (client.getUsername().equals("admin") && client.getPassword().equals("admin")) {
                    forAdmin();
                } else {
                    forUsers(client);
                }

            } else if (answer == 2) {
                client = registration();
                forUsers(client);
            }
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
    }

    public void forUsers(Client client) {
        try {

            System.out.println("Hello " + client.getName() + " " + client.getSurname());
            while (true) {
                System.out.println("Choose an option");
                System.out.println("    1.Car rent \n    2.All cars \n    3.Available cars\n    4.My cars\n    5.Close");
                int num = scan.nextInt();
                if (num == 1) {
                    while (true) {
                        rentCar(client.getId());
                        System.out.println("Write \"5\" to Exit");
                        int Break = scan.nextInt();
                        if (Break == 5) {
                            break;
                        }
                    }
                } else if (num == 2) {
                    while (true) {
                        showAllCars();
                        System.out.println("Write \"5\" to Exit");
                        int Break = scan.nextInt();
                        if (Break == 5) {
                            break;
                        }
                    }
                } else if (num == 3) {
                    while (true) {
                        showAllAvailableCars();
                        System.out.println("Write \"5\" to Exit");
                        int Break = scan.nextInt();
                        if (Break == 5) {
                            break;
                        }
                    }
                } else if (num == 4) {
                    while (true) {
                        int clientID = client.getId();
                        getMyCars(clientID);
                        int Break = scan.nextInt();
                        if (Break == 5) {
                            break;
                        }
                    }
                } else if (num == 5) {
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
    }

    public void forAdmin() {
        try {
            System.out.println("Hello admin");
            while (true) {
                System.out.println("Choose an option");
                System.out.println("    1.Get all users \n    2.All cars \n    3.Available cars\n    4.Add Car\n    5.Close");
                int num = scan.nextInt();
                if (num == 1) {
                    while (true) {
                        getAllClients();
                        System.out.println("Write \"5\" to Exit");
                        int Break = scan.nextInt();
                        if (Break == 5) {
                            break;
                        }
                    }

                } else if (num == 2) {
                    while (true) {
                        showAllCars();
                        System.out.println("Write \"5\" to Exit");
                        int Break = scan.nextInt();
                        if (Break == 5) {
                            break;
                        }
                    }

                } else if (num == 3) {
                    while (true) {
                        showAllAvailableCars();
                        System.out.println("Write \"5\" to Exit");
                        int Break = scan.nextInt();
                        if (Break == 5) {
                            break;
                        }
                    }

                } else if (num == 4) {
                    while (true) {
                        addCar();
                        System.out.println("Car successfully added to database");
                        System.out.println("Write \"5\" to Exit");
                        int Break = scan.nextInt();
                        if (Break == 5) {
                            break;
                        }
                    }

                } else if (num == 5) {
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
    }

    public Client registration() {
        Client client = new Client();
        System.out.println("create account");
        System.out.println("First name: ");
        client.setName(scan.next());
        System.out.println("Second name: ");
        client.setSurname(scan.next());
        System.out.println("Username: ");
        client.setUsername(scan.next());
        System.out.println("Password: ");
        client.setPassword(scan.next());
        System.out.println("Telephone: ");
        client.setTelephone(scan.next());

        return clientController.Registration(client);
    }

    public Client sign_in() {
        System.out.println("Write your username and password");
        System.out.print("Username:");
        String username = scan.next();
        System.out.print("Password:");
        String password = scan.next();

        return clientController.Sign_in(username, password);
    }

    public void showAllCars() {
        List<Car> cars = carController.showAllCars();
        for (Car c : cars) {
            System.out.println(c.getId() + " " + c.getBrand() + " " + c.getModel() + " " + c.getYear() + " " + c.getType() + " " + c.getColor() + " \"" + c.getPricePerDay() + "\" price per day");
        }
    }

    public void showAllAvailableCars() {
        List<Car> cars = carController.showAllAvailableCars();
        for (Car c : cars) {
            System.out.println(c.getId() + " " + c.getBrand() + " " + c.getModel() + " " + c.getYear() + " " + c.getType() + " " + c.getColor() + " \"" + c.getPricePerDay() + "\" price per day");
        }
    }

    public void rentCar(int clientID) throws InterruptedException {
        System.out.println("Available cars:");
        showAllAvailableCars();
        int answer = scan.nextInt();
        System.out.println("How many days do you want to rent a car?");
        int days = scan.nextInt();
        Car car = carController.rentCar(answer, clientID);
        System.out.println("you want to rent a   \"" + car.getBrand() + "\"  for  " + days + " days");
        int total = days * car.getPricePerDay();
        System.out.println("Total price: " + total);
        System.out.println("1.Continue\n2.Back");
        int num = scan.nextInt();
        if (num == 1) {
            System.out.println("Payment");
            System.out.print("Card number:");
            String cardNum = scan.next();
            System.out.print("Сardholder name:");
            String name = scan.next();
            System.out.print("Expiration:");
            String timeNum = scan.next();
            System.out.print("CVV:");
            String cvv = scan.next();
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(".");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Payment has passed");

        }
    }

    public void getAllClients() {
        List<Client> clients = clientController.getAllClients();
        for (Client cl : clients) {
            System.out.println(cl.getName() + " " + cl.getSurname() + " " + cl.getUsername() + " " +
                    cl.getPassword() + " " + cl.getTelephone());
        }
    }

    public Car addCar() {
        Car car = new Car();
        System.out.println("Enter the car data:");
        System.out.println("Year: ");
        car.setYear(scan.nextInt());
        System.out.println("Brand: ");
        car.setBrand(scan.next());
        System.out.println("Model: ");
        car.setModel(scan.next());
        System.out.println("Type: ");
        car.setType(scan.next());
        System.out.println("Color: ");
        car.setColor(scan.next());
        System.out.println("Price per day: ");
        car.setPricePerDay(scan.nextInt());

        return carController.addCar(car);
    }

    public void getMyCars(int clientID) {
        List<Car> cars = carController.getMyCars(clientID);
        for (Car c : cars) {
            System.out.println(c.getId() + " " + c.getBrand() + " " + c.getModel() + " " + c.getYear() + " " + c.getType() + " " + c.getColor());
        }

    }


}
