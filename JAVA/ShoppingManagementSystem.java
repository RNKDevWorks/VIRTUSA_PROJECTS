import java.util.*;

class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void reduceStock(int qty) {
        stock -= qty;
    }

    public void increaseStock(int qty) {
        stock += qty;
    }

    public void display() {
        System.out.println(id + " | " + name + " | ₹" + price + " | Stock: " + stock);
    }
}

class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    public void increaseQty(int qty) {
        quantity += qty;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }

    public void display() {
        System.out.println(product.getName() + " | Qty: " + quantity + " | ₹" + getTotal());
    }
}

class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int qty) {

        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.increaseQty(qty);
                System.out.println("Quantity updated in cart!");
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

        System.out.println("Total: ₹" + total);
    }

    public double checkout() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty!");
            return 0;
        }

        double total = 0;

        System.out.println("\nBILL");
        System.out.println("----------------------------");

        for (CartItem item : items) {
            item.display();
            total += item.getTotal();
            item.getProduct().reduceStock(item.getQuantity());
        }

        System.out.println("----------------------------");
        System.out.println("Total Amount: ₹" + total);

        items.clear();
        return total;
    }
}

class Store {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        for (Product prod : products) {
            if (prod.getId() == p.getId()) {
                System.out.println("Product ID already exists!");
                return;
            }
        }

        products.add(p);
        System.out.println("Product added!");
    }

    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

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

    public void searchProduct(String keyword) {
        boolean found = false;

        for (Product p : products) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                p.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching product found!");
        }
    }
}

public class ShoppingManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static Store store = new Store();
    static Cart cart = new Cart();

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("\n===== Shopping Management System =====");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Add to Cart");
                System.out.println("4. View Cart");
                System.out.println("5. Checkout");
                System.out.println("6. Search Product");
                System.out.println("7. Exit");

                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1: addProductUI(); break;
                    case 2: store.viewProducts(); break;
                    case 3: addToCartUI(); break;
                    case 4: cart.viewCart(); break;
                    case 5: cart.checkout(); break;
                    case 6: searchUI(); break;
                    case 7: System.exit(0);
                    default: System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
                sc.nextLine(); 
            }
        }
    }

    static void addProductUI() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Stock: ");
        int stock = sc.nextInt();

        store.addProduct(new Product(id, name, price, stock));
    }

    static void addToCartUI() {
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
    }

    static void searchUI() {
        sc.nextLine();
        System.out.print("Enter product name: ");
        String keyword = sc.nextLine();
        store.searchProduct(keyword);
    }
}