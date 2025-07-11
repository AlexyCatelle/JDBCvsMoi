package jdbcExo1.dao;
// Afficher le compte avec les infos clients


import jdbcExo1.entity.BankAccount;
import jdbcExo1.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String request;

    public void createTable(){
        String sql = """
            CREATE TABLE IF NOT EXISTS bank_account (
                id INT PRIMARY KEY AUTO_INCREMENT,
     customer_id INT NOT NULL,
     total_amount DOUBLE PRECISION NOT NULL
            );
        """;

        try (Connection connection = DatabaseManager.getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.err.println("Erreur lors de la création de la table bank_account : " + e.getMessage());
        }
    }

    public BankAccount createBankAccount(BankAccount bankAccount){
       try {
           connection = DatabaseManager.getConnection();
           request = "INSERT INTO bank_account (customer_id, total_amount) VALUES (?, ?)";
           preparedStatement = connection.prepareStatement(request,Statement.RETURN_GENERATED_KEYS);
preparedStatement.setInt(1, bankAccount.getCustomerId());
preparedStatement.setDouble(2, bankAccount.getTotalAmount());
preparedStatement.executeUpdate();
resultSet = preparedStatement.getGeneratedKeys();
if(resultSet.next()){
    bankAccount.setId(resultSet.getInt(1));
}
return bankAccount;

       }catch (SQLException e) {
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



}
