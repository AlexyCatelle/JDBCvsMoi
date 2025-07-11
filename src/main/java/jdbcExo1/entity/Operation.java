package jdbcExo1.entity;

import jdbcExo1.Enum.OperationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Operation {
    private int id;
    private double amount;
    private OperationType Type;
    private int BankAccountId;
}
