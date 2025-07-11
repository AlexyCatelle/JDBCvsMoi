package jdbcExo1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class BankAccount {
    private int id;
    private int customerId;
    private double totalAmount;
}
