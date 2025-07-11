package jdbcExo1.dao;

import jdbcExo1.entity.Customer;
import jdbcExo1.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String request;

    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS customer (
                id INT PRIMARY KEY AUTO_INCREMENT,
                first_name VARCHAR(100),
                last_name VARCHAR(100),
                phone VARCHAR(20)
            );
        """;

        try (Connection connection = DatabaseManager.getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.err.println("Erreur lors de la création de la table customer : " + e.getMessage());
        }
    }

    public Customer createCustomer(Customer customer) {

        try {
connection = DatabaseManager.getConnection();
request = "INSERT INTO customer (first_name, last_name, phone) VALUES (?,?,?)";
preparedStatement = connection.prepareStatement(request,Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,customer.getFirstName());
preparedStatement.setString(2,customer.getLastName());
preparedStatement.setString(3,customer.getPhone());
preparedStatement.executeUpdate();
resultSet = preparedStatement.getGeneratedKeys();

if(resultSet.next()) {
    customer.setId(resultSet.getInt(1));
}
return customer;

        } catch (SQLException e) {
            System.out.println("Error during saving customer : "+e.getMessage());
            return null;
        }finally {
            try{
                connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public Boolean update(Customer customer) {
        try{
            connection = DatabaseManager.getConnection();
            request = "UPDATE customer SET first_name=?,last_name=? WHERE id=?";
preparedStatement = connection.prepareStatement(request);
preparedStatement.setString(1,customer.getFirstName());
preparedStatement.setString(2,customer.getLastName());
preparedStatement.setInt(3,customer.getId());
int rows = preparedStatement.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            System.out.println("Error during updating customer : "+e.getMessage());
            return false;
        }finally {
            try{
                connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public Boolean delete(int id) {
        try {
            connection = DatabaseManager.getConnection();
            request = "DELETE FROM customer WHERE id=?";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1,id);
            int rows = preparedStatement.executeUpdate();

            return rows == 1;
        } catch (SQLException e) {
            System.out.println("Error during deleting customer : "+e.getMessage());
            return false;
        }finally {
            try{
                connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public Customer getById(int id){
        try {
            connection = DatabaseManager.getConnection();
            request = "SELECT * FROM customer WHERE id=?";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return Customer.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .phone(resultSet.getString("phone"))
                        .build();
            }
          return null;

        } catch (SQLException e) {
            System.out.println("Error during reading customer : "+e.getMessage());
            return null;
        }finally {
            try{
                connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Customer> getAll(){
        try {
            connection = DatabaseManager.getConnection();
            List<Customer> customers = new ArrayList<>();
            request = "SELECT * FROM customer";
            preparedStatement = connection.prepareStatement(request);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                customers.add(Customer.builder()
                                .id(resultSet.getInt("id"))
                                .firstName(resultSet.getString("first_name"))
                                .lastName(resultSet.getString("last_name"))
                                .phone(resultSet.getString("phone"))
                        .build());
            }
            return customers;

        } catch (SQLException e) {
            System.out.println("Error during listing customers : "+e.getMessage());
            return new ArrayList<>();
        }finally {
            try{
                connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
