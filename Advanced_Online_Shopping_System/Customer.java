package Advanced_Online_Shopping_System;

class Customer {
    private String customerId;
    private String customerName;
    private String email;
    private String address;
    private String phone;
    private ShoppingCart shoppingCart;

    public Customer(String customerId, String customerName, String email, String address, String phone) {
        if (!validateCustomerDetails(email, phone)) {
            throw new IllegalArgumentException("Invalid customer details");
        }
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.shoppingCart = new ShoppingCart(this);
    }

    private boolean validateCustomerDetails(String email, String phone) {
        if (email == null || !email.contains("@")) {
            System.out.println("Error: Invalid email format");
            return false;
        }
        if (phone == null || phone.length() < 10) {
            System.out.println("Error: Phone number must be at least 10 digits");
            return false;
        }
        return true;
    }

    public boolean validateAddress() {
        if (address == null || address.split(",").length < 3) {
            System.out.println("Error: Address must include street, city, and postal code");
            return false;
        }
        return true;
    }

    // Getters
    public String getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public ShoppingCart getShoppingCart() { return shoppingCart; }

    @Override
    public String toString() {
        return String.format("Customer ID: %s\nName: %s\nEmail: %s\nPhone: %s\nAddress: %s",
                customerId, customerName, email, phone, address);
    }
}
