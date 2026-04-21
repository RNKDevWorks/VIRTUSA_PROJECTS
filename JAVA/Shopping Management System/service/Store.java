package service;

import java.util.*;
import model.Product;

public class Store {
    private List<Product> products = new ArrayList<>();

    public Store() {
        products.add(new Product(1, "Laptop", 50000, 10));
        products.add(new Product(2, "Phone", 20000, 15));
        products.add(new Product(3, "Headphones", 2000, 30));
    }

    public void addProduct(Product p) {
        products.add(p);
        System.out.println("Product added!");
    }

    public void viewProducts() {
        for (Product p : products) {
            p.display();
        }
    }

    public Product findProduct(int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}