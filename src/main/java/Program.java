import com.desenvigor.dao.AccountDAO;
import com.desenvigor.dao.ClientDAO;
import com.desenvigor.dao.EmployeeDAO;
import com.desenvigor.model.*;

public class Program {
    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAO();
        AccountDAO accDao = new AccountDAO();
        EmployeeDAO empDAO = new EmployeeDAO();


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
        clientDAO.insert(newEmp);


    }
}
