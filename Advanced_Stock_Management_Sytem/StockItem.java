package Advanced_Stock_Management_Sytem;

abstract class StockItem {
    protected String itemId;
    protected String itemName;
    protected int quantityInStock;
    protected double pricePerUnit;
    protected String category;
    protected String supplier;

    public StockItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String category, String supplier) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantityInStock = quantityInStock;
        this.pricePerUnit = pricePerUnit;
        this.category = category;
        this.supplier = supplier;
    }

    // Abstract methods
    public abstract void updateStock(int quantity);
    public abstract double calculateStockValue();
    public abstract String generateStockReport();
    public abstract boolean validateStock();

    // Common methods
    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public int getQuantityInStock() { return quantityInStock; }
    public double getPricePerUnit() { return pricePerUnit; }
    public String getCategory() { return category; }
    public String getSupplier() { return supplier; }

    protected boolean validateCommonFields() {
        if (quantityInStock < 0) {
            System.out.println("Error: Stock quantity cannot be negative for " + itemName);
            return false;
        }
        if (pricePerUnit <= 0) {
            System.out.println("Error: Price per unit must be positive for " + itemName);
            return false;
        }
        if (itemName == null || itemName.trim().isEmpty()) {
            System.out.println("Error: Item name cannot be empty");
            return false;
        }
        return true;
    }
}