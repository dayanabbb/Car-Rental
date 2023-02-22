package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.entities.Car;
import com.company.entities.Client;
import com.company.repositories.interfaces.ICarRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CarRepository implements ICarRepository {
    private IDB DB;

    public CarRepository(IDB DB) {
        this.DB = DB;
    }

    @Override
    public List<Car> showAllCars() {
        Connection connection;
        try {
            connection = DB.getConnection();
            String AllCars = "select * from cars order by id";
            PreparedStatement preparedStatement = connection.prepareStatement(AllCars);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getInt("id"), resultSet.getInt("year"), resultSet.getString("brand"), resultSet.getString("model"),
                        resultSet.getString("type"), resultSet.getString("color"), resultSet.getInt("price"));
                cars.add(car);
            }
            return cars;
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }

    @Override
    public List<Car> showAllAvailableCars() {
        Connection connection;
        try {
            connection = DB.getConnection();
            String availableCars = "SELECT * FROM cars where owner_id is null";
            PreparedStatement preparedStatement = connection.prepareStatement(availableCars);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getInt("id"), resultSet.getInt("year"), resultSet.getString("brand"), resultSet.getString("model"),
                        resultSet.getString("type"), resultSet.getString("color"), resultSet.getInt("price"));
                cars.add(car);
            }
            return cars;
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }

    @Override
    public Car rentCar(int id, int clientID) {

        Car car = null;
        Scanner scan = new Scanner(System.in);
        Connection connection;
        try {
            connection = DB.getConnection();
            String Cars = "select * from cars where id = ?";
            String update = "update cars S ";
            PreparedStatement preparedStatement = connection.prepareStatement(Cars);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (!resultSet.isBeforeFirst()) {
                System.out.println("we do not have that car, enter another id");
                id = scan.nextInt();
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();

            }
            int ID = id;
            String query = String.format("update %s set owner_id = %s where id = %s", "cars", clientID, ID);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            while (resultSet.next()) {
                car = new Car(resultSet.getInt("id"), resultSet.getInt("year"), resultSet.getString("brand"), resultSet.getString("model"),
                        resultSet.getString("type"), resultSet.getString("color"), resultSet.getInt("price"), resultSet.getInt("owner_id"));
            }

            return car;
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }

    @Override
    public Car addCar(Car car) {
        Connection connection = null;
        try {
            connection = DB.getConnection();
            String SQL_INSERT = "insert into cars (year, brand, model, type , color, price) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, car.getYear());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setString(4, car.getType());
            preparedStatement.setString(5, car.getColor());
            preparedStatement.setInt(6, car.getPricePerDay());
            preparedStatement.execute();

            return new Car(car.getYear(), car.getBrand(), car.getModel(), car.getType(), car.getColor(), car.getPricePerDay());

        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }

    @Override
    public List<Car> getMyCars(int clientID) {
        Connection connection;
        try {
            connection = DB.getConnection();
            String myCars = "select * from cars where owner_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(myCars);
            preparedStatement.setInt(1, clientID);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getInt("id"), resultSet.getInt("year"), resultSet.getString("brand"), resultSet.getString("model"),
                        resultSet.getString("type"), resultSet.getString("color"), resultSet.getInt("price"));
                cars.add(car);
            }
            return cars;
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }

    @Override
    public Car payment(int id, int clientID){
        Scanner scan = new Scanner(System.in);

        Connection connection;
        try {
            connection = DB.getConnection();
            System.out.println("Данные карты");
            System.out.print("Card number:");
            String cardNum = scan.next();
            System.out.print("Сardholder name:");
            String name = scan.next();
            System.out.print("Expiration:");
            String timeNum = scan.next();
            System.out.print("CVV:");
            String cvv = scan.next();
            String query = String.format("update %s set owner_id = %s where id = %s", "cars", clientID, id);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            TimeUnit.SECONDS.sleep(1); System.out.print("."); TimeUnit.SECONDS.sleep(1); System.out.print("."); TimeUnit.SECONDS.sleep(1); System.out.println("."); TimeUnit.SECONDS.sleep(1);
            System.out.println("Payment has passed");

        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }

        return null;
    }
}
