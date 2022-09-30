import com.desenvigor.dao.AccountDAO;
import com.desenvigor.dao.ClientDAO;
import com.desenvigor.dao.EmployeeDAO;
import com.desenvigor.model.Client;
import com.desenvigor.model.CurrentAccount;
import com.desenvigor.model.Employee;
import com.desenvigor.model.SavingsAccount;

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
        EmployeeDAO empDAO = new EmployeeDAO();
        boolean autheticated = false;

        while (!autheticated) {
            System.out.print("1 - Login Employee \n2 - Create Employee\n");
            int opt = sc.nextInt();
            sc.nextLine();

            if (opt == 1) {
               autheticated = employeeAuthentication(sc);
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
            }

        }




        /*

        //CreateClient
        Client newClient = new Client();
        clientDAO.insert(newClient); //Insert client into database

        //Create Savings Account
        Account newSavAcc = new SavingsAccount();
        accDao.insert(newSavAcc); //Save account into database

        //Create Current Account
        Account newCurAcc = new CurrentAccount();
        accDao.insert(newCurAcc); //Save account into database

        //Create Employee
        Employee newEmp = new Employee();
        clientDAO.insert(newEmp); //Save Employee into database
        */

    }

    private static void createNewUser(Scanner sc) {
        ClientDAO clientDAO = new ClientDAO();
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
        if (clientAccount.toLowerCase().equals("ca")){
            accNumber++;
            CurrentAccount clientAcc = new CurrentAccount(newClient, "000" + accNumber, "000-1", new BigDecimal(clientInitialDeposit), 0.03);
        } else {
            accNumber++;
            SavingsAccount clientAcc = new SavingsAccount(newClient, "000" + accNumber, "000-1", new BigDecimal(clientInitialDeposit), 0.03);
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
