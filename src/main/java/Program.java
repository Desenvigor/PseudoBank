import com.desenvigor.dao.AccountDAO;
import com.desenvigor.dao.ClientDAO;
import com.desenvigor.dao.EmployeeDAO;
import com.desenvigor.model.Account;
import com.desenvigor.model.Client;

public class Program {
    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAO();
        AccountDAO accDao = new AccountDAO();
        EmployeeDAO empDAO = new EmployeeDAO();
        Client client = clientDAO.find(1L);
        Client client2 = clientDAO.find(2L);
        Client employee = empDAO.findById(10L);

        Account empAccount = accDao.find(11L);
        Account clientAccount = accDao.find(8L);
        Account client2Account = accDao.find(9L);

        System.out.println(client);
        System.out.println(clientAccount);
        System.out.println(client2);
        System.out.println(client2Account);
        System.out.println(employee);
        System.out.println(empAccount);
    }
}
