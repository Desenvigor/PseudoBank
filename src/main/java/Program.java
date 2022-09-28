import com.desenvigor.dao.AccountDAO;
import com.desenvigor.model.Account;
import com.desenvigor.model.Operation;
import com.desenvigor.model.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pseudoBank");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT t from Transaction t where operation = " + Operation.WITHDRAW.ordinal();

        List<Transaction> trans = em.createQuery(jpql,Transaction.class).getResultList();
        AccountDAO accDAO = new AccountDAO();

        Account acc = accDAO.find(19L);

        acc.withdraw("20.00");
        System.out.println(acc.getBalance());

        accDAO.update(acc);

        trans.forEach(transs -> {
            System.out.println(transs);
        });
    }
}
