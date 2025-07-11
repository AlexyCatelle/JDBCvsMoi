package jdbcExo1.service;

import jdbcExo1.dao.BankAccountDAO;
import jdbcExo1.entity.BankAccount;

import java.sql.SQLException;
import java.util.List;

public class BankAccountService {
    private BankAccountDAO bankAccountDAO;

    public BankAccountService() { bankAccountDAO = new BankAccountDAO(); };

    public Boolean createBankAccount(int customerId, double totalAmount) {
        bankAccountDAO.createTable();
        BankAccount bankAccount = new BankAccount().builder()
                .customerId(customerId)
                .totalAmount(totalAmount)
                .build();
        return bankAccountDAO.createBankAccount(bankAccount) != null;
    }

}
