package org.example;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * - SRP: Startpunkt; orkestrerar tjänster, hanterar fel (try/catch) och demoar kraven.
 * - Loggning: Viktiga händelser loggas via SLF4J.
 */
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        DataStore store = new DataStore();
        OrderService orderService = new OrderService(store);
        AnalysisService analysis = new AnalysisService(store);
        ReportPrinter printer = new ReportPrinter();

        // Seed: Produkter (List<Product>)
        Product p1 = new Product("P-100", "Kaffe", "Dryck", 45.0);
        Product p2 = new Product("P-101", "Te", "Dryck", 35.0);
        Product p3 = new Product("P-200", "Choklad", "Snacks", 25.0);
        Product p4 = new Product("P-201", "Chips", "Snacks", 22.5);
        Product p5 = new Product("P-300", "Mjölk", "Mejeri", 19.0);

        store.addProduct(p1);
        store.addProduct(p2);
        store.addProduct(p3);
        store.addProduct(p4);
        store.addProduct(p5);

        // Demo: Orders + try/catch robusthet
        try {
            // giltig order
            orderService.placeOrder("O-1", "Ali", List.of(p1, p3, p3));
            orderService.placeOrder("O-2", "Sara", List.of(p2, p4));
            orderService.placeOrder("O-3", "Ali", List.of(p5, p1));

            // ogiltig (tom) för att visa EmptyOrderException
            // orderService.placeOrder("O-4", "Jonas", List.of());

            // ogiltig (produkt ej i katalog) för att visa ProductNotFoundException
            // Product fake = new Product("FAKE", "Fake", "X", 1.0);
            // orderService.placeOrder("O-5", "Ali", List.of(fake));
        } catch (EmptyOrderException | ProductNotFoundException ex) {
            log.warn("Fel vid orderläggning: {}", ex.getMessage());
        } catch (Exception ex) {
            log.error("Oväntat fel", ex);
        }

        // Streams: 1) Filtrera kategori + sortera stigande pris
        var drycker = analysis.findProductsByCategorySorted("Dryck");
        printer.printProducts("Produkter i kategorin 'Dryck' (pris stigande)", drycker);

        // Streams: 2) Totalvärde för kund
        double totalAli = analysis.calculateCustomerTotal("Ali");
        System.out.printf("Totalvärde för kund Ali: %.2f SEK%n", totalAli);

        // Streams: 3) Topp 3 mest köpta produkter
        System.out.println("Top 3 mest köpta produkter:");
        analysis.top3MostPurchasedProducts().forEach(printer::printTop3);
    }
}



