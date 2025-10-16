package org.example;


import java.util.Objects;


public class Product {
    private final String id;      // unikt id
    private final String name;    // produktnamn
    private final String category;// kategori
    private final double price;   // pris i SEK

    public Product(String id, String name, String category, double price) {
        // Enkel validering
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id får ej vara tomt");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name får ej vara tomt");
        if (category == null || category.isBlank()) throw new IllegalArgumentException("category får ej vara tomt");
        if (price < 0) throw new IllegalArgumentException("price får ej vara negativt");
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Getters (inkapsling)
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public boolean equals(Object o) {
        // Viktigt för Set/Map
        if (this == o) return true;
        if (!(o instanceof Product p)) return false;
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "%s (%s) - %.2f SEK".formatted(name, category, price);
    }
}
