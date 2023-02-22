package com.company.data;
import com.company.data.interfaces.IDB;
import java.sql.*;

public class PostgresDB implements IDB {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionUrl = "jdbc:postgresql://localhost:5432/Car_Rental";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(connectionUrl, "postgres", "55810579");

            return connection;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}