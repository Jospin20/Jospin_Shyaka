package Advanced_Stock_Management_Sytem;

import java.util.ArrayList;
import java.util.List;

class Warehouse {
    private String warehouseId;
    private String location;
    private double capacity; // in cubic meters
    private String managerName;
    private List<StockItem> inventory;

    public Warehouse(String warehouseId, String location, double capacity, String managerName) {
        this.warehouseId = warehouseId;
        this.location = location;
        this.capacity = capacity;
        this.managerName = managerName;
        this.inventory = new ArrayList<>();
    }

    public void addItemToInventory(StockItem item) {
        inventory.add(item);
        System.out.println(item.getItemName() + " added to warehouse " + warehouseId);
    }

    public void removeItemFromInventory(String itemId) {
        inventory.removeIf(item -> item.getItemId().equals(itemId));
    }

    public String generateInventoryReport() {
        StringBuilder report = new StringBuilder();
        report.append(String.format("Warehouse %s (%s) Inventory Report\n", warehouseId, location));
        report.append(String.format("Manager: %s, Capacity: %.2f m³\n\n", managerName, capacity));

        double totalValue = 0;
        for (StockItem item : inventory) {
            report.append(item.generateStockReport()).append("\n\n");
            totalValue += item.calculateStockValue();
        }

        report.append(String.format("Total Inventory Value: $%.2f\n", totalValue));
        report.append(String.format("Items in stock: %d", inventory.size()));

        return report.toString();
    }

    // Getters
    public String getWarehouseId() { return warehouseId; }
    public String getLocation() { return location; }
    public double getCapacity() { return capacity; }
    public String getManagerName() { return managerName; }
    public List<StockItem> getInventory() { return inventory; }
}
