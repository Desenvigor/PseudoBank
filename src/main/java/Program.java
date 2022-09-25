import com.desenvigor.dao.EmployeeDAO;

public class Program {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();

        System.out.println(dao.findById(23L));
    }
}
