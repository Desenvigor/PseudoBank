import com.desenvigor.dao.AccountDAO;
import com.desenvigor.dao.ClientDAO;
import com.desenvigor.dao.EmployeeDAO;
import com.desenvigor.dao.TransactionDAO;
import com.desenvigor.model.*;
import com.desenvigor.vo.TransactionsReportVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    private static int accNumber = 3;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClientDAO clientDAO = new ClientDAO();
        AccountDAO accDao = new AccountDAO();
        boolean authenticated = false;
        TransactionDAO transDAO = new TransactionDAO();

        List<TransactionsReportVO> transactions = transDAO.reportTransfer();
        transactions.forEach(System.out::println);

        while (!authenticated) {
            System.out.print("1 - Login Employee \n2 - Create Employee\n");
            int opt = sc.nextInt();
            sc.nextLine();

            if (opt == 1) {
               authenticated = employeeAuthentication(sc);
                System.out.println("Login successful");
            } else {
                createNewEmployee(sc);
            }

            System.out.print("1 - Create User\n2 - Select User\n");
            int userOpt = sc.nextInt();

            if (userOpt == 1){
                createNewUser(sc);
            } else {
                List<Client> allClients = clientDAO.findAll();
                allClients.forEach(client -> System.out.println(client.getId() +". "+ client));
                System.out.println("Select the client: ");
                int id = sc.nextInt();
                Client client = clientDAO.find(Long.parseLong(Integer.toString(id)));
                Account acc = accDao.findByClient(client.getId());
                System.out.println("Select operation, deposit or withdraw [d/w]: ");
                String operation = sc.nextLine();
                if (operation.equalsIgnoreCase("d")){
                    System.out.println("Insert the value: ");
                    String value = sc.nextLine();
                    acc.deposit(value);
                } else if (operation.equalsIgnoreCase("w")){
                    System.out.println("Insert the value: ");
                    String value = sc.nextLine();
                    acc.withdraw(value);
                }
            }

        }

    }

    private static void createNewUser(Scanner sc) {
        ClientDAO clientDAO = new ClientDAO();
        AccountDAO accDao = new AccountDAO();
        System.out.print("Insert client name: ");
        String clientName = sc.nextLine();
        System.out.print("Insert client SSN: ");
        String clientSsn = sc.nextLine();
        System.out.print("Insert birthdate [dd/MM/yyyy]: ");
        String clientBirthdate = sc.nextLine();
        System.out.print("What type is the account[CA/SA]: ");
        String clientAccount = sc.nextLine();
        System.out.println("Initial deposit: ");
        String clientInitialDeposit = sc.nextLine();

        Client newClient = new Client(clientName, clientSsn, new Date(clientBirthdate));
        clientDAO.insert(newClient);
        if (clientAccount.equalsIgnoreCase("ca")){
            accNumber++;
            CurrentAccount clientAcc = new CurrentAccount(newClient, "000" + accNumber, "000-1", new BigDecimal(clientInitialDeposit), 0.03);
            accDao.insert(clientAcc);
        } else {
            accNumber++;
            SavingsAccount clientAcc = new SavingsAccount(newClient, "000" + accNumber, "000-1", new BigDecimal(clientInitialDeposit), 0.03);
            accDao.insert(clientAcc);
        }

    }

    private static boolean employeeAuthentication(Scanner sc) {
        EmployeeDAO empDAO = new EmployeeDAO();

        System.out.print("Insert your credential: ");
        String empCredential = sc.nextLine();
        try {
            Employee emp = empDAO.findByCredential(empCredential);
            if (emp != null){
                return true;
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("Employee not found. " + e.getMessage());
        }
        return false;
    }

    private static void createNewEmployee(Scanner sc) {
        ClientDAO clientDAO = new ClientDAO();
        System.out.print("Insert employee name: ");
        String empName = sc.nextLine();
        System.out.print("Insert employee SSN: ");
        String empSsn = sc.nextLine();
        System.out.print("Insert birthdate [dd/MM/yyyy]: ");
        String empBirthdate = sc.nextLine();
        System.out.print("Insert employee credential: ");
        String empCredential = sc.nextLine();

        Employee newEmp = new Employee(empName, empSsn, new Date(empBirthdate), empCredential);
        clientDAO.insert(newEmp);
    }
}
