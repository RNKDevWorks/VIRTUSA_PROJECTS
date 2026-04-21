package model;

import java.util.*;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int qty) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.increaseQty(qty);
                System.out.println("Quantity updated!");
                return;
            }
        }
        items.add(new CartItem(product, qty));
        System.out.println("Added to cart!");
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        double total = 0;
        for (CartItem item : items) {
            item.display();
            total += item.getTotal();
        }

        System.out.println("Total: " + total);
    }

    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        double total = 0;

        System.out.println("\nBILL");
        System.out.println("----------------");

        for (CartItem item : items) {
            item.display();
            total += item.getTotal();
            item.getProduct().reduceStock(item.getQuantity());
        }

        System.out.println("----------------");
        System.out.println("Total: " + total);

        items.clear();
    }
}