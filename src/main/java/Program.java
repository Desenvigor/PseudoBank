import com.desenvigor.dao.AccountDAO;
import com.desenvigor.dao.ClientDAO;
import com.desenvigor.dao.EmployeeDAO;
import com.desenvigor.model.Employee;

import java.util.Date;
import java.util.Scanner;

public class Program {
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
            } else {
                createNewEmployee(sc);
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
