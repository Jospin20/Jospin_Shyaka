package Advanced_Online_Shopping_System;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlineShoppingSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<ShoppingItem> inventory = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Payment> payments = new ArrayList<>();
    private static Customer currentCustomer = null;

    public static void main(String[] args) {
        initializeSampleData();

        System.out.println("=== Welcome to Advanced Online Shopping System ===");

        boolean running = true;
        while (running) {
            if (currentCustomer == null) {
                showMainMenu();
            } else {
                showCustomerMenu();
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    if (currentCustomer == null) registerCustomer();
                    else browseInventory();
                    break;
                case 2:
                    if (currentCustomer == null) loginCustomer();
                    else viewCart();
                    break;
                case 3:
                    if (currentCustomer == null) running = false;
                    else checkout();
                    break;
                case 4:
                    if (currentCustomer != null) logout();
                    break;
                case 5:
                    if (currentCustomer != null) generateReports();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for using our system. Goodbye!");
    }

    private static void showMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void showCustomerMenu() {
        System.out.println("\nCustomer Menu (" + currentCustomer.getCustomerName() + "):");
        System.out.println("1. Browse Inventory");
        System.out.println("2. View Shopping Cart");
        System.out.println("3. Checkout");
        System.out.println("4. Logout");
        System.out.println("5. Generate Reports");
        System.out.print("Enter your choice: ");
    }

    private static void registerCustomer() {
        System.out.println("\nCustomer Registration");
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Address (street,city,postal): ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        try {
            Customer customer = new Customer(id, name, email, address, phone);
            customers.add(customer);
            currentCustomer = customer;
            System.out.println("Registration successful! You are now logged in.");
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private static void loginCustomer() {
        System.out.println("\nCustomer Login");
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();

        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(id)) {
                currentCustomer = customer;
                System.out.println("Login successful! Welcome back, " + customer.getCustomerName());
                return;
            }
        }

        System.out.println("Customer not found. Please register first.");
    }

    private static void logout() {
        System.out.println("Goodbye, " + currentCustomer.getCustomerName() + "!");
        currentCustomer = null;
    }

    private static void browseInventory() {
        System.out.println("\n=== Product Inventory ===");
        for (int i = 0; i < inventory.size(); i++) {
            ShoppingItem item = inventory.get(i);
            System.out.printf("%d. %s - $%.2f (%d in stock)\n",
                    i+1, item.getItemName(), item.getPrice(), item.getStockAvailable());
        }

        System.out.print("\nEnter item number to view details (0 to go back): ");
        int itemNum = scanner.nextInt();
        scanner.nextLine();

        if (itemNum == 0) return;
        if (itemNum < 1 || itemNum > inventory.size()) {
            System.out.println("Invalid item number.");
            return;
        }

        ShoppingItem selectedItem = inventory.get(itemNum - 1);
        System.out.println("\n" + selectedItem.generateInvoice());

        System.out.print("\nAdd to cart? (Y/N): ");
        String addToCart = scanner.nextLine();
        if (addToCart.equalsIgnoreCase("Y")) {
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            if (selectedItem.addToCart(currentCustomer, quantity)) {
                System.out.println("Item added to cart!");
            }
        }
    }

    private static void viewCart() {
        ShoppingCart cart = currentCustomer.getShoppingCart();
        System.out.println("\n" + cart.generateCartSummary());

        System.out.println("\nOptions:");
        System.out.println("1. Update quantity");
        System.out.println("2. Remove item");
        System.out.println("3. Back to menu");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter item name to update: ");
                String itemName = scanner.nextLine();
                System.out.print("Enter new quantity: ");
                int newQty = scanner.nextInt();
                scanner.nextLine();

                for (CartItem item : cart.getCartItems()) {
                    if (item.getItem().getItemName().equalsIgnoreCase(itemName)) {
                        cart.updateQuantity(item.getItem().getItemId(), newQty);
                        return;
                    }
                }
                System.out.println("Item not found in cart.");
                break;
            case 2:
                System.out.print("Enter item name to remove: ");
                String removeName = scanner.nextLine();

                for (CartItem item : cart.getCartItems()) {
                    if (item.getItem().getItemName().equalsIgnoreCase(removeName)) {
                        cart.removeItem(item.getItem().getItemId());
                        return;
                    }
                }
                System.out.println("Item not found in cart.");
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void checkout() {
        if (!currentCustomer.validateAddress()) {
            System.out.println("Please update your address before checkout.");
            return;
        }

        ShoppingCart cart = currentCustomer.getShoppingCart();
        if (cart.getCartItems().isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout.");
            return;
        }

        System.out.println("\n=== Checkout Summary ===");
        System.out.println(cart.generateCartSummary());

        System.out.println("\nSelect Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Bank Transfer");
        System.out.print("Enter choice: ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine();

        String paymentMethod;
        switch (paymentChoice) {
            case 1: paymentMethod = "Credit Card"; break;
            case 2: paymentMethod = "PayPal"; break;
            case 3: paymentMethod = "Bank Transfer"; break;
            default:
                System.out.println("Invalid choice. Defaulting to Credit Card.");
                paymentMethod = "Credit Card";
        }

        Payment payment = new Payment(paymentMethod, cart.getTotalPrice());
        if (payment.processPayment()) {
            payments.add(payment);
            System.out.println("\n" + payment.generateReceipt());

            // Update stock
            for (CartItem item : cart.getCartItems()) {
                item.getItem().updateStock(-item.getQuantity());
            }

            // Clear cart
            cart.getCartItems().clear();
            cart.updateTotalPrice();
        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }

    private static void generateReports() {
        System.out.println("\n=== Sales Reports ===");

        // Sales summary
        double totalRevenue = payments.stream().mapToDouble(Payment::getAmountPaid).sum();
        System.out.printf("Total Revenue: $%.2f\n", totalRevenue);

        // Payment methods breakdown
        System.out.println("\nPayment Method Breakdown:");
        long creditCardCount = payments.stream().filter(p -> p.getPaymentMethod().equals("Credit Card")).count();
        long paypalCount = payments.stream().filter(p -> p.getPaymentMethod().equals("PayPal")).count();
        long bankTransferCount = payments.stream().filter(p -> p.getPaymentMethod().equals("Bank Transfer")).count();

        System.out.printf("Credit Card: %d payments\n", creditCardCount);
        System.out.printf("PayPal: %d payments\n", paypalCount);
        System.out.printf("Bank Transfer: %d payments\n", bankTransferCount);

        // Inventory status
        System.out.println("\nInventory Status:");
        for (ShoppingItem item : inventory) {
            System.out.printf("%s: %d in stock\n", item.getItemName(), item.getStockAvailable());
        }
    }

    private static void initializeSampleData() {
        // Sample electronics
        ElectronicsItem tv = new ElectronicsItem("ELEC001", "4K Smart TV", "55-inch 4K UHD Smart TV", 899.99, 15, 24);
        tv.validateItem();
        inventory.add(tv);

        // Sample clothing
        ClothingItem shirt = new ClothingItem("CLOTH001", "Men's Casual Shirt", "100% Cotton Button Shirt", 29.99, true);
        shirt.addSizeStock("M", 10);
        shirt.addSizeStock("L", 8);
        shirt.validateItem();
        inventory.add(shirt);

        // Sample groceries
        GroceriesItem milk = new GroceriesItem("GROC001", "Organic Milk", "1 Gallon Organic Whole Milk", 5.99, 30, "2023-12-31");
        milk.validateItem();
        inventory.add(milk);

        // Sample books
        BooksItem novel = new BooksItem("BOOK001", "The Great Novel", "Bestselling fiction novel", 14.99, 20,
                "9781234567890", "First Edition", "Standard");
        novel.validateItem();
        inventory.add(novel);

        // Sample accessories
        AccessoriesItem watch = new AccessoriesItem("ACC001", "Sports Watch", "Waterproof Digital Watch", 49.99, 25);
        watch.addColor("Black");
        watch.addColor("Blue");
        watch.addReview(new Review("John", "Great watch!", 5));
        watch.validateItem();
        inventory.add(watch);

        // Sample customers
        Customer customer1 = new Customer("CUST001", "John Doe", "john@example.com", "123 Main St,Anytown,12345", "5551234567");
        customers.add(customer1);
    }
}
