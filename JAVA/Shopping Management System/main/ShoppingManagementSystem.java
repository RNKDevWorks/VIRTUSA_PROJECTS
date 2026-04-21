package main;

import java.util.*;
import user.*;
import service.Store;

public class ShoppingManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static Store store = new Store();

    public static void main(String[] args) {

        users.add(new Admin("admin", "123"));
        users.add(new Customer("user", "123"));

        while (true) {
            System.out.println("\n==== Welcome ====");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    signup();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void login() {
        sc.nextLine();
        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        for (User u : users) {
            if (u.getUsername().equals(username) && u.checkPassword(password)) {
                System.out.println("Login successful!");
                u.showMenu(store, sc);
                return;
            }
        }

        System.out.println("Invalid credentials!");
    }

    static void signup() {
        sc.nextLine();
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.println("Select role:");
        System.out.println("1. Customer");
        System.out.println("2. Admin");

        int role = sc.nextInt();

        if (role == 1) {
            users.add(new Customer(username, password));
        } else {
            users.add(new Admin(username, password));
        }

        System.out.println("Signup successful!");
    }
}