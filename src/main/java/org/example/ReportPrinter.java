package org.example;





import java.util.List;
import java.util.Map;

/*
 * - SRP: Ansvarar endast för utskrift/presentation i konsol.
 */
public class ReportPrinter {

    public void printProducts(String title, List<org.example.Product> products) {
        System.out.println("== " + title + " ==");
        products.forEach(p -> System.out.println(" - " + p));
    }

    public void printTop3(Map.Entry<org.example.Product, Long> entry) {
        System.out.println(entry.getKey() + "  | antal: " + entry.getValue());
    }
}
