package Advanced_Online_Shopping_System;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Payment {
    private String paymentId;
    private String paymentMethod; // "Credit Card", "PayPal", "Bank Transfer"
    private double amountPaid;
    private LocalDateTime transactionDate;
    private boolean isCompleted;

    public Payment(String paymentMethod, double amountToPay) {
        this.paymentId = "PAY-" + System.currentTimeMillis();
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountToPay;
        this.transactionDate = LocalDateTime.now();
        this.isCompleted = false;
    }

    public boolean processPayment() {
        if (!validatePaymentMethod()) {
            System.out.println("Error: Invalid payment method");
            return false;
        }

        // Simulate payment processing
        System.out.println("Processing " + paymentMethod + " payment of $" + amountPaid);
        try {
            Thread.sleep(2000); // Simulate processing delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        this.isCompleted = true;
        System.out.println("Payment successful!");
        return true;
    }

    private boolean validatePaymentMethod() {
        return paymentMethod.equals("Credit Card") ||
                paymentMethod.equals("PayPal") ||
                paymentMethod.equals("Bank Transfer");
    }

    public String generateReceipt() {
        return String.format("=== Payment Receipt ===\n" +
                        "Payment ID: %s\nMethod: %s\nAmount: $%.2f\nDate: %s\nStatus: %s",
                paymentId, paymentMethod, amountPaid,
                transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                isCompleted ? "Completed" : "Pending");
    }

    // Getters
    public String getPaymentId() { return paymentId; }
    public String getPaymentMethod() { return paymentMethod; }
    public double getAmountPaid() { return amountPaid; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public boolean isCompleted() { return isCompleted; }
}