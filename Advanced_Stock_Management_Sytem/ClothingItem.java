package Advanced_Stock_Management_Sytem;

import java.util.HashMap;
import java.util.Map;

class ClothingItem extends StockItem {
    private Map<String, Map<String, Integer>> sizeColorStock; // size -> (color -> quantity)
    private boolean hasDiscount;

    public ClothingItem(String itemId, String itemName, double pricePerUnit, String supplier) {
        super(itemId, itemName, 0, pricePerUnit, "Clothing", supplier);
        this.sizeColorStock = new HashMap<>();
        this.hasDiscount = false;
    }

    public void addSizeColorStock(String size, String color, int quantity) {
        sizeColorStock.putIfAbsent(size, new HashMap<>());
        sizeColorStock.get(size).put(color, sizeColorStock.get(size).getOrDefault(color, 0) + quantity);
        quantityInStock += quantity;
    }

    @Override
    public void updateStock(int quantity) {
        System.out.println("For clothing items, please use addSizeColorStock to update specific sizes/colors");
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit * (hasDiscount ? 0.9 : 1.0);
    }

    @Override
    public String generateStockReport() {
        StringBuilder report = new StringBuilder();
        report.append(String.format("Clothing Item Report:\nID: %s\nName: %s\nTotal Quantity: %d\nPrice: $%.2f\n",
                itemId, itemName, quantityInStock, pricePerUnit));

        report.append("Size/Color Breakdown:\n");
        for (String size : sizeColorStock.keySet()) {
            for (String color : sizeColorStock.get(size).keySet()) {
                report.append(String.format("- %s/%s: %d\n", size, color, sizeColorStock.get(size).get(color)));
            }
        }
        report.append(String.format("Stock Value: $%.2f\nDiscount Applied: %s",
                calculateStockValue(), hasDiscount ? "Yes (10%)" : "No"));

        return report.toString();
    }

    @Override
    public boolean validateStock() {
        if (!validateCommonFields()) return false;
        if (sizeColorStock.isEmpty()) {
            System.out.println("Error: Clothing item must have size/color information");
            return false;
        }
        return true;
    }

    public void applyDiscount() {
        this.hasDiscount = true;
        System.out.println("10% discount applied to " + itemName);
    }

    public Map<String, Map<String, Integer>> getSizeColorStock() { return sizeColorStock; }
    public boolean hasDiscount() { return hasDiscount; }
}
