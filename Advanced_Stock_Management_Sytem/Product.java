package Advanced_Stock_Management_Sytem;

class Product {
    private String productId;
    private String productName;
    private String brand;
    private String supplier;
    private int stockQuantity;

    public Product(String productId, String productName, String brand, String supplier, int stockQuantity) {
        if (!validateInputs(productName, stockQuantity, brand)) {
            throw new IllegalArgumentException("Invalid product data");
        }
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.supplier = supplier;
        this.stockQuantity = stockQuantity;
    }

    private boolean validateInputs(String productName, int stockQuantity, String brand) {
        if (productName == null || productName.trim().isEmpty()) {
            System.out.println("Error: Product name cannot be empty");
            return false;
        }
        if (stockQuantity < 0) {
            System.out.println("Error: Stock quantity cannot be negative");
            return false;
        }
        if (brand == null || brand.trim().isEmpty()) {
            System.out.println("Error: Brand cannot be empty");
            return false;
        }
        return true;
    }

    // Getters
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getBrand() { return brand; }
    public String getSupplier() { return supplier; }
    public int getStockQuantity() { return stockQuantity; }

    @Override
    public String toString() {
        return String.format("Product ID: %s\nName: %s\nBrand: %s\nSupplier: %s\nStock: %d",
                productId, productName, brand, supplier, stockQuantity);
    }
}