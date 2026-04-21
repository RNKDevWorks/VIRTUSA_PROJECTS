package user;

import java.util.Scanner;
import model.*;
import service.Store;

public class Customer extends User {

    private Cart cart = new Cart();

    public Customer(String username, String password) {
        super(username, password);
    }

    @Override
    public void showMenu(Store store, Scanner sc) {
        while (true) {
            System.out.println("\n--- CUSTOMER MENU ---");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Logout");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    store.viewProducts();
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    Product p = store.findProduct(id);

                    if (p == null) {
                        System.out.println("Product not found!");
                    } else if (p.getStock() < qty) {
                        System.out.println("Not enough stock!");
                    } else {
                        cart.addItem(p, qty);
                    }
                    break;

                case 3:
                    cart.viewCart();
                    break;

                case 4:
                    cart.checkout();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}