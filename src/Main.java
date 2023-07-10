import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {


        SellerDAO sellerDAO = DAOFactory.createSellerDAO();

        System.out.println("=== TEST 1: seller findById ===");
        System.out.println(sellerDAO.findById(3));

        System.out.println("\n=== TEST 2: seller findByDepartment ===");
        sellerDAO.findByDepartment(new Department(2, null)).forEach(System.out::println);

        System.out.println("\n=== TEST 3: seller findAll ===");
        sellerDAO.findAll().forEach(System.out::println);

        System.out.println("\n=== TEST 4: seller insert ===");
        Seller newSeller = new Seller(null, "Carlos", "carlos@gmail.com", LocalDate.now(), 3000.0, new Department(1, "contas"));
                sellerDAO.insert(newSeller);
        System.out.println("Inserted! New ID = " + newSeller.getId());

        System.out.println("\n=== TEST 5: seller update ===");
        Seller newSeller1 = sellerDAO.findById(1);
        newSeller1.setName("Jacinto Pinto");
        sellerDAO.update(newSeller1);
        System.out.println("Updated Completed!");

        System.out.println("\n=== TEST 6: seller delete ===");
        sellerDAO.deleteById(7);
        System.out.println("Delete Completed!");

    }
}