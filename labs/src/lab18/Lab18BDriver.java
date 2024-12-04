package lab18;

import java.sql.*;

public class Lab18BDriver {
    public static void main(String[] args) {

        String url = "jdbc:sqlite:dogsdb.sqlite3";
        try (Connection connection = DriverManager.getConnection(url)) {
            System.out.println("all good");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from dogs");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
            String createTable = "CREATE TABLE IF NOT EXISTS breeds" +
                    "(breed TEXT PRIMARY KEY, description TEXT)";
            statement.executeUpdate(createTable);
            String data1 = "INSERT INTO breeds(breed, description) VALUES ('Mutt', 'Mangy')";
            statement.executeUpdate(data1);
            data1 = "INSERT INTO breeds(breed, description) VALUES ('Beagle', 'Cute')";
            statement.executeUpdate(data1);
            resultSet = statement.executeQuery("select * from breeds");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("breed") + " " +
                        resultSet.getString("description"));
            }



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
