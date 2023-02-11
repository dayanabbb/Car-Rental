import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "55810579";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Car_Rental";
    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String login = null;
        String password = null;
        Client client = new Client();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
            System.out.println("Welcome to the ROD");
            System.out.println("1.Sign in\n2.Sign up");
            int res = scan.nextInt();
            if (res == 2) {
                Registration();
                //System.out.println("Thank you for registration, " + client.getName()+" " + client.getSurname());
            } else if (res == 1) {
                System.out.print("login:");
                login = scan.next();
                System.out.print("password:");
                password = scan.next();
                client = Sign_in(login, password);
            }
        }

        public static void Registration() {
            String SQL_INSERT = "insert into clients (name, surname, login, password , telephone) values (?, ?, ?, ?, ?)";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
                String name, surname, login, password, telephone;
                System.out.println("First name: ");
                name = scan.nextLine();
                preparedStatement.setString(1, name);
                System.out.println("Second name: ");
                surname = scan.nextLine();
                preparedStatement.setString(2, surname);
                System.out.println("Login: ");//check for uniqueness
                login = scan.nextLine();
                preparedStatement.setString(3, login);
                System.out.println("Password: ");//check for 1-8, upper case, restricted symbols
                password = scan.nextLine();
                preparedStatement.setString(4, password);
                System.out.println("Telephone: ");
                telephone = scan.nextLine();
                preparedStatement.setString(5, telephone);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public static Client Sign_in(String login, String password) {
            Client client = new Client();
            String DB = "select * from clients where login = ?";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(DB)) {
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    while (!resultSet.getString("password").equals(password) || !resultSet.getString("login").equals(login)) {
                        System.out.println("try again");
                        password = scan.next();
                    }
                    client = new Client(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("telephone"));

                }
            } catch (Exception e) {
                System.out.println(e);
            }

            return client;
        }
    }
}
