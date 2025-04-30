package Advanced_Stock_Management_Sytem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<StockItem> stockItems = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    private static List<Supplier> suppliers = new ArrayList<>();
    private static List<Warehouse> warehouses = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Advanced Stock Management System ===");
        initializeSampleData();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: addStockItem(); break;
                case 2: viewAllStockItems(); break;
                case 3: updateStockItem(); break;
                case 4: generateInventoryReport(); break;
                case 5: manageProducts(); break;
                case 6: manageSuppliers(); break;
                case 7: manageWarehouses(); break;
                case 8: running = false; System.out.println("Exiting system..."); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add Stock Item");
        System.out.println("2. View All Stock Items");
        System.out.println("3. Update Stock Item");
        System.out.println("4. Generate Inventory Report");
        System.out.println("5. Manage Products");
        System.out.println("6. Manage Suppliers");
        System.out.println("7. Manage Warehouses");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void initializeSampleData() {
        // Sample suppliers
        suppliers.add(new Supplier("SUP001", "TechGadgets Inc.", "John Smith", "555-1234", "john@techgadgets.com"));
        suppliers.add(new Supplier("SUP002", "FashionWorld Ltd.", "Sarah Johnson", "555-5678", "sarah@fashionworld.com"));

        // Sample warehouse
        warehouses.add(new Warehouse("WH001", "Main Warehouse", 5000, "Michael Brown"));

        // Sample electronics item
        ElectronicsItem tv = new ElectronicsItem("ELEC001", "4K Smart TV", 15, 899.99, "SUP001", 24);
        tv.validateStock();
        stockItems.add(tv);
        warehouses.get(0).addItemToInventory(tv);

        // Sample clothing item
        ClothingItem shirt = new ClothingItem("CLOTH001", "Men's Casual Shirt", 29.99, "SUP002");
        shirt.addSizeColorStock("M", "Blue", 10);
        shirt.addSizeColorStock("L", "Red", 8);
        shirt.validateStock();
        stockItems.add(shirt);
        warehouses.get(0).addItemToInventory(shirt);
    }

    private static void addStockItem() {
        System.out.println("\nSelect Item Category:");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.println("3. Grocery");
        System.out.println("4. Furniture");
        System.out.println("5. Perishable");
        System.out.print("Enter choice: ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Item ID: ");
        String itemId = scanner.nextLine();
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Price per Unit: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Supplier: ");
        String supplier = scanner.nextLine();

        StockItem item = null;

        switch (categoryChoice) {
            case 1:
                System.out.print("Enter Warranty Period (months): ");
                int warranty = scanner.nextInt();
                item = new ElectronicsItem(itemId, itemName, quantity, price, supplier, warranty);
                break;
            case 2:
                item = new ClothingItem(itemId, itemName, price, supplier);
                System.out.print("How many size/color combinations? ");
                int combos = scanner.nextInt();
                scanner.nextLine();
                for (int i = 0; i < combos; i++) {
                    System.out.print("Enter size: ");
                    String size = scanner.nextLine();
                    System.out.print("Enter color: ");
                    String color = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = scanner.nextInt();
                    scanner.nextLine();
                    ((ClothingItem)item).addSizeColorStock(size, color, qty);
                }
                break;
            case 3:
                System.out.print("Enter Expiration Date (yyyy-MM-dd): ");
                String expDate = scanner.nextLine();
                item = new GroceryItem(itemId, itemName, quantity, price, supplier, expDate);
                break;
            case 4:
                System.out.print("Enter Weight (kg): ");
                double weight = scanner.nextDouble();
                item = new FurnitureItem(itemId, itemName, quantity, price, supplier, weight);
                break;
            case 5:
                System.out.print("Enter Expiration Date (yyyy-MM-dd): ");
                String perishableExpDate = scanner.nextLine();
                System.out.print("Enter Shelf Life (days): ");
                int shelfLife = scanner.nextInt();
                item = new PerishableItem(itemId, itemName, quantity, price, supplier, perishableExpDate, shelfLife);
                break;
            default:
                System.out.println("Invalid category choice.");
                return;
        }

        if (item != null && item.validateStock()) {
            stockItems.add(item);
            System.out.println("Item added successfully!");
            if (!warehouses.isEmpty()) {
                warehouses.get(0).addItemToInventory(item);
            }
        } else {
            System.out.println("Failed to add item. Validation failed.");
        }
    }

    private static void viewAllStockItems() {
        if (stockItems.isEmpty()) {
            System.out.println("No stock items available.");
            return;
        }

        System.out.println("\n=== All Stock Items ===");
        for (StockItem item : stockItems) {
            System.out.println("------------------------");
            System.out.println(item.generateStockReport());
        }
    }

    private static void updateStockItem() {
        if (stockItems.isEmpty()) {
            System.out.println("No stock items available to update.");
            return;
        }

        System.out.println("\nSelect Item to Update:");
        for (int i = 0; i < stockItems.size(); i++) {
            System.out.printf("%d. %s (ID: %s)\n", i+1, stockItems.get(i).getItemName(), stockItems.get(i).getItemId());
        }

        System.out.print("Enter item number: ");
        int itemNum = scanner.nextInt();
        if (itemNum < 1 || itemNum > stockItems.size()) {
            System.out.println("Invalid item number.");
            return;
        }

        StockItem item = stockItems.get(itemNum - 1);
        System.out.println("\nSelected Item:");
        System.out.println(item.generateStockReport());

        System.out.println("\nUpdate Options:");
        System.out.println("1. Update Quantity");
        System.out.println("2. Apply Discount (Electronics/Clothing only)");
        System.out.println("3. Mark as Packed (Furniture only)");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter quantity change (+/-): ");
                int qtyChange = scanner.nextInt();
                item.updateStock(qtyChange);
                break;
            case 2:
                if (item instanceof ElectronicsItem) {
                    System.out.print("Enter discount percentage (0-50): ");
                    double discount = scanner.nextDouble() / 100;
                    ((ElectronicsItem)item).applyDiscount(discount);
                } else if (item instanceof ClothingItem) {
                    ((ClothingItem)item).applyDiscount();
                } else {
                    System.out.println("Discounts only available for Electronics and Clothing items.");
                }
                break;
            case 3:
                if (item instanceof FurnitureItem) {
                    ((FurnitureItem)item).markAsPacked();
                } else {
                    System.out.println("Only furniture items can be marked as packed.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void generateInventoryReport() {
        if (warehouses.isEmpty()) {
            System.out.println("No warehouses available to generate report.");
            return;
        }

        System.out.println("\nSelect Warehouse for Report:");
        for (int i = 0; i < warehouses.size(); i++) {
            System.out.printf("%d. %s (%s)\n", i+1,
                    warehouses.get(i).getWarehouseId(),
                    warehouses.get(i).getLocation());
        }

        System.out.print("Enter warehouse number: ");
        int warehouseNum = scanner.nextInt();
        if (warehouseNum < 1 || warehouseNum > warehouses.size()) {
            System.out.println("Invalid warehouse number.");
            return;
        }

        System.out.println("\n" + warehouses.get(warehouseNum - 1).generateInventoryReport());
    }

    private static void manageProducts() {
        boolean back = false;
        while (!back) {
            System.out.println("\nProduct Management:");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Brand: ");
                    String brand = scanner.nextLine();
                    System.out.print("Enter Supplier: ");
                    String supplier = scanner.nextLine();
                    System.out.print("Enter Stock Quantity: ");
                    int qty = scanner.nextInt();

                    try {
                        Product product = new Product(id, name, brand, supplier, qty);
                        products.add(product);
                        System.out.println("Product added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error creating product: " + e.getMessage());
                    }
                    break;
                case 2:
                    if (products.isEmpty()) {
                        System.out.println("No products available.");
                    } else {
                        System.out.println("\n=== All Products ===");
                        for (Product p : products) {
                            System.out.println("----------------------");
                            System.out.println(p);
                        }
                    }
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void manageSuppliers() {
        boolean back = false;
        while (!back) {
            System.out.println("\nSupplier Management:");
            System.out.println("1. Add Supplier");
            System.out.println("2. View All Suppliers");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Supplier ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Company Name: ");
                    String company = scanner.nextLine();
                    System.out.print("Enter Contact Person: ");
                    String contact = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    try {
                        Supplier supplier = new Supplier(id, company, contact, phone, email);
                        suppliers.add(supplier);
                        System.out.println("Supplier added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error creating supplier: " + e.getMessage());
                    }
                    break;
                case 2:
                    if (suppliers.isEmpty()) {
                        System.out.println("No suppliers available.");
                    } else {
                        System.out.println("\n=== All Suppliers ===");
                        for (Supplier s : suppliers) {
                            System.out.println("----------------------");
                            System.out.println(s);
                        }
                    }
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void manageWarehouses() {
        boolean back = false;
        while (!back) {
            System.out.println("\nWarehouse Management:");
            System.out.println("1. Add Warehouse");
            System.out.println("2. View All Warehouses");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Warehouse ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter Capacity (m³): ");
                    double capacity = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Manager Name: ");
                    String manager = scanner.nextLine();

                    warehouses.add(new Warehouse(id, location, capacity, manager));
                    System.out.println("Warehouse added successfully!");
                    break;
                case 2:
                    if (warehouses.isEmpty()) {
                        System.out.println("No warehouses available.");
                    } else {
                        System.out.println("\n=== All Warehouses ===");
                        for (Warehouse w : warehouses) {
                            System.out.println("----------------------");
                            System.out.println("Warehouse ID: " + w.getWarehouseId());
                            System.out.println("Location: " + w.getLocation());
                            System.out.println("Capacity: " + w.getCapacity() + " m³");
                            System.out.println("Manager: " + w.getManagerName());
                            System.out.println("Items in inventory: " + w.getInventory().size());
                        }
                    }
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
