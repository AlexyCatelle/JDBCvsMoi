package jdbcExo1.util;

import jdbcExo1.service.CustomerService;
import jdbcExo1.entity.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ConsoleIHM {
private static CustomerService customerService = new CustomerService();
    private Scanner scanner = new Scanner(System.in);

    public void start(){
boolean running = true;
while (running) {
    System.out.println("1. Créer un client.");
    System.out.println("2. Dépot d'argent sur le compte.");
    System.out.println("3. Retrait d'argent sur le compte.");
    System.out.println("4. Afficher le compte d'un client.");
    System.out.println("5. Quitter");
    System.out.print("Choix : ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    switch (choice) {
        case 1:
            createCustomer();
            break;
            case 2:
                System.out.println("Ici dépot argent sur le compte.");
                break;
                case 3:
                    System.out.println("Ici dépot argent sur le compte.");
                    break;
                    case 4:
                        DisplayCustomer();
                        break;
                        case 5:
                            running = false;
                            break;
                            default:
                                System.out.println("Choix invalide !");

    }
}
        System.out.println("Au revoir !");
    }
    private void createCustomer() {
        try {
            System.out.print("Nom  : ");
            String lastName = scanner.nextLine();
            System.out.print("Prenom : ");
            String firstName = scanner.nextLine();
            System.out.print("Téléphone :");
            String phone = scanner.nextLine();

            customerService.createCustomer(firstName,lastName,phone);
        }catch (SQLException e) {
            System.out.println("Error (from consoleIHM) during creating customer : "+e.getMessage());
        }

    }

    private void DisplayCustomer() {
            System.out.print("ID du client : ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Customer customer = customerService.getByIdCustomer(id);
            if (customer != null) {
                System.out.println("Nom : " + customer.getLastName());
                System.out.println("Prénom : " + customer.getFirstName());
                System.out.println("Téléphone : " + customer.getPhone());
            } else {
                System.out.println("Client non trouvé.");
            }
    }
}
