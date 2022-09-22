import com.desenvigor.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Program {
    public static void main(String[] args) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");

        Client client = new Client("Elaine", "00000000000", new Date("09/04/92"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pseudoBank");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();


        client = null;
        System.out.println(client);

        client = em.find(Client.class, 1L);
        System.out.println(client);
        em.close();
    }
}
