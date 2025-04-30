package Advanced_Motor_Vehicle_System;

import java.time.LocalDate;
import java.util.Scanner;

public class InsuranceSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Collect vehicle details
        System.out.println("Enter vehicle make:");
        String make = scanner.nextLine();

        System.out.println("Enter vehicle model:");
        String model = scanner.nextLine();

        System.out.println("Enter vehicle year:");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter vehicle type:");
        String type = scanner.nextLine();

        Vehicle vehicle = new Vehicle("V001", make, model, year, type);

        // Collect person details
        System.out.println("Enter policy holder's full name:");
        String fullName = scanner.nextLine();

        System.out.println("Enter policy holder's email:");
        String email = scanner.nextLine();

        System.out.println("Enter policy holder's phone:");
        String phone = scanner.nextLine();

        System.out.println("Enter policy holder's birth year:");
        int birthYear = scanner.nextInt();
        System.out.println("Enter birth month:");
        int birthMonth = scanner.nextInt();
        System.out.println("Enter birth day:");
        int birthDay = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        LocalDate dob = LocalDate.of(birthYear, birthMonth, birthDay);
        Person policyHolder = new Person("P001", fullName, dob, email, phone);

        // Choose insurance policy type
        System.out.println("Choose insurance policy type (1-Comprehensive, 2-Third Party, 3-Collision, 4-Liability, 5-Roadside Assistance):");
        int policyChoice = scanner.nextInt();

        InsurancePolicy policy = null;
        switch (policyChoice) {
            case 1:
                policy = new ComprehensivePolicy("CP001", vehicle, policyHolder, 10000, 500, LocalDate.now(), LocalDate.now().plusYears(1));
                break;
            case 2:
                System.out.println("Enter engine capacity:");
                double engineCapacity = scanner.nextDouble();
                policy = new ThirdPartyPolicy("TP001", vehicle, policyHolder, 5000, 300, engineCapacity, LocalDate.now(), LocalDate.now().plusYears(1));
                break;
            case 3:
                System.out.println("Did vehicle pass safety check? (true/false):");
                boolean safetyCheckPassed = scanner.nextBoolean();
                policy = new CollisionPolicy("CL001", vehicle, policyHolder, 8000, 400, safetyCheckPassed, LocalDate.now(), LocalDate.now().plusYears(1));
                break;
            case 4:
                System.out.println("Did policyholder pass medical check? (true/false):");
                boolean medicalCheckPassed = scanner.nextBoolean();
                policy = new LiabilityPolicy("LP001", vehicle, policyHolder, 6000, 350, medicalCheckPassed, LocalDate.now(), LocalDate.now().plusYears(1));
                break;
            case 5:
                System.out.println("Is vehicle registration verified? (true/false):");
                boolean registrationVerified = scanner.nextBoolean();
                policy = new RoadsideAssistancePolicy("RA001", vehicle, policyHolder, 4000, 250, registrationVerified, LocalDate.now(), LocalDate.now().plusYears(1));
                break;
            default:
                System.out.println("Invalid choice!");
                System.exit(1);
        }

        // Display policy details and calculate premium
        System.out.println("\nPolicy Selected:");
        System.out.println(policy.generatePolicyReport());
        System.out.println("Calculated Premium: $" + policy.calculatePremium());

        // Process a sample claim
        System.out.println("\nEnter claim amount:");
        double claimAmount = scanner.nextDouble();
        boolean claimProcessed = policy.processClaim(claimAmount);

        System.out.println(claimProcessed ? "Claim approved!" : "Claim denied: Amount exceeds coverage.");

        scanner.close();
    }
}
