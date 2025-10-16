package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class AnalysisService {
    private static final Logger log = LoggerFactory.getLogger(AnalysisService.class);
    private final DataStore dataStore;

    public AnalysisService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /** Hitta alla produkter i en viss kategori, sorterade stigande efter pris. */
    public List<Product> findProductsByCategorySorted(String category) {
        log.info("Filtrerar produkter på kategori: {}", category);
        return dataStore.getAllProducts().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category)) // filter
                .sorted(Comparator.comparingDouble(Product::getPrice))    // sort
                .toList();                                               // terminal
    }

    /** Beräkna totalvärdet av en kunds orderhistorik. */
    public double calculateCustomerTotal(String customerName) {
        log.info("Beräknar total för kund: {}", customerName);
        return dataStore.getOrdersByCustomer().getOrDefault(customerName, List.of()).stream()
                .flatMap(order -> order.getProducts().stream()) // platta ut produkter
                .mapToDouble(Product::getPrice)                 // transformera till double
                .sum();                                         // terminal
    }

    /** Hitta de tre mest köpta produkterna totalt (top 3). */
    public List<Map.Entry<Product, Long>> top3MostPurchasedProducts() {
        log.info("Beräknar tre mest köpta produkterna");

        // 1) Alla produkter i alla order -> stream
        // 2) Gruppera på produkt -> räkna
        // 3) Sortera på count desc -> ta 3
        return dataStore.getAllOrders().stream()
                .flatMap(o -> o.getProducts().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Product, Long>comparingByValue().reversed())
                .limit(3)
                .toList();
    }
}
