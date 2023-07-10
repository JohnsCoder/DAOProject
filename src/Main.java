import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Department dep = new Department(1, "books");
        Seller sell = new Seller(2, "Carlos", "carlos@email.com", LocalDate.now(), 4000.0, dep);
        System.out.println(sell);
    }
}