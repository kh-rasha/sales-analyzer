package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class DataStore {
    private static final Logger log = LoggerFactory.getLogger(DataStore.class);
    private final List<org.example.Product> allProducts = new ArrayList<>();
    private final List<org.example.Order> allOrders = new ArrayList<>();
    private final Map<String, List<org.example.Order>> ordersByCustomer = new HashMap<>();

    public DataStore() {
    }

    public List<org.example.Product> getAllProducts() {
        return Collections.unmodifiableList(allProducts);
    }

    public List<org.example.Order> getAllOrders() {
        return Collections.unmodifiableList(allOrders);
    }

    public Map<String, List<org.example.Order>> getOrdersByCustomer() {
        // Omodifierbar vy för kapsling
        return Collections.unmodifiableMap(ordersByCustomer);

    }
    public void addProduct(org.example.Product p) {
        // Enkel regel: unika id redan garanteras i Product.equals/hashCode
        allProducts.add(p);
        log.info("Produkt tillagd: {}", p);
    }

    public void addOrder(org.example.Order order) {
        allOrders.add(order);
        ordersByCustomer.computeIfAbsent(order.getCustomerName(), k -> new ArrayList<>()).add(order);
        log.info("Order tillagd: {} för kund {}", order.getOrderId(), order.getCustomerName());
    }
}

