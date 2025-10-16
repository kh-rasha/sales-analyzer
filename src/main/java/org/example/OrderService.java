package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final org.example.DataStore dataStore;
    public OrderService(org.example.DataStore dataStore) {
        this.dataStore = dataStore;
    }
    public void placeOrder(String orderId, String customerName, List<org.example.Product> products) {
        if (products == null || products.isEmpty()) {
            log.warn("Tom order upptäckt {}", orderId);
            throw new org.example.EmptyOrderException("Order saknar produkter");
        }
        // Säkerställ att alla produkter finns i katalogen (robusthet)
        List<org.example.Product> catalog = dataStore.getAllProducts();
        for (org.example.Product p : products) {
            if (!catalog.contains(p)) {
                log.error("Produkt saknas i katalogen: {}", p.getId());
                throw new org.example.ProductNotFoundException("Produkt saknas: " + p.getId());
            }
        }
        dataStore.addOrder(new org.example.Order(orderId, customerName, products));
        log.info("Order {} lagd för kund {}", orderId, customerName);
    }
}