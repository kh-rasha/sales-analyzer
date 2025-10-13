package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * - SRP: Hålla ordningsdata (orderId, kundnamn, produkter).
 * - Lämplig datastruktur: List<Product> för att bevara ordning och möjliggöra dubbletter.
 *   Motivering: En kund kan köpa samma produkt flera gånger i en order -> List.
 */
public class Order {
    private final String orderId;        // unikt id för ordern
    private final String customerName;   // kundens namn
    private final List<Product> products;// produkterna i denna order

    public Order(String orderId, String customerName, List<Product> products) {
        if (orderId == null || orderId.isBlank()) throw new IllegalArgumentException("orderId tomt");
        if (customerName == null || customerName.isBlank()) throw new IllegalArgumentException("customer tomt");
        // Försvarskopia för immutabilitet
        this.orderId = orderId;
        this.customerName = customerName;
        this.products = new ArrayList<>(products == null ? List.of() : products);
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }

    public List<Product> getProducts() {
        // Omodifierbar vy för kapsling
        return Collections.unmodifiableList(products);
    }
}