import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Department dep = new Department(1, "books");


        SellerDAO sellerDAO = DAOFactory.createSellerDAO();

        System.out.println("=== TEST 1: seller findById ===");
        System.out.println(sellerDAO.findById(3));
    }
}