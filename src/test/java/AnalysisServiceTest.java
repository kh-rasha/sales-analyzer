
package org.example;

import com.store.domain.Product;
import com.store.repo.DataStore;
import com.store.service.AnalysisService;
import com.store.service.OrderService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnalysisServiceTest {

    @Test
    void calculateCustomerTotal_should_sum_products() {
        DataStore store = new DataStore();
        AnalysisService analysis = new AnalysisService(store);
        OrderService orderService = new OrderService(store);

        Product p1 = new Product("P1", "A", "X", 10);
        Product p2 = new Product("P2", "B", "X", 20);
        store.addProduct(p1);
        store.addProduct(p2);

        orderService.placeOrder("O1", "Ali", List.of(p1, p2, p1)); // total = 10+20+10 = 40

        assertEquals(40.0, analysis.calculateCustomerTotal("Ali"), 0.0001);
    }
}
