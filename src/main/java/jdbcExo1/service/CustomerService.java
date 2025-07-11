package jdbcExo1.service;

import jdbcExo1.dao.CustomerDAO;
import jdbcExo1.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public Boolean createCustomer(String firstName, String lastName, String phone) throws SQLException {
        customerDAO.createTable();
        Customer customer = new Customer().builder()
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .build();
        return customerDAO.save(customer) != null;
    }

    public Boolean updateCustomer(Customer customer) {
        return customerDAO.update(customer);
    }
    public Boolean deleteCustomer(int id) {
        return customerDAO.delete(id) != null;
    }
    public Customer getByIdCustomer(int id) {
        return customerDAO.getById(id);
    }
    public List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }
}
