package org.example;



/**  Kastas när en efterfrågad produkt saknas i katalogen/databasen. */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) { super(message); }
}

