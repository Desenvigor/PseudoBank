import com.desenvigor.dao.ClientDAO;
import com.desenvigor.model.Client;
import com.desenvigor.model.CurrentAccount;
import com.desenvigor.model.SavingsAccount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Program {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pseudoBank");
        EntityManager em = emf.createEntityManager();
        ClientDAO clientDAO = new ClientDAO();

        Client client = clientDAO.find(1L);
        Client client2 = clientDAO.find(2L);
        SavingsAccount account1 = new SavingsAccount(client, "0001", "000-1", new BigDecimal("4000.0"), 0.2);
        CurrentAccount account2 = new CurrentAccount(client2, "0002", "000-1", new BigDecimal("3000.0"), 0.3);

        em.getTransaction().begin();
        em.persist(account1);
        em.persist(account2);
        em.getTransaction().commit();
        em.close();
    }
}
