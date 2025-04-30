package Advanced_Stock_Management_Sytem;

class Supplier {
    private String supplierId;
    private String companyName;
    private String contactPerson;
    private String phone;
    private String email;

    public Supplier(String supplierId, String companyName, String contactPerson, String phone, String email) {
        if (!validateContactDetails(phone, email)) {
            throw new IllegalArgumentException("Invalid supplier contact details");
        }
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
    }

    private boolean validateContactDetails(String phone, String email) {
        if (phone == null || phone.trim().isEmpty()) {
            System.out.println("Error: Phone cannot be empty");
            return false;
        }
        if (email == null || !email.contains("@")) {
            System.out.println("Error: Invalid email format");
            return false;
        }
        return true;
    }

    // Getters
    public String getSupplierId() { return supplierId; }
    public String getCompanyName() { return companyName; }
    public String getContactPerson() { return contactPerson; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("Supplier ID: %s\nCompany: %s\nContact: %s\nPhone: %s\nEmail: %s",
                supplierId, companyName, contactPerson, phone, email);
    }
}
