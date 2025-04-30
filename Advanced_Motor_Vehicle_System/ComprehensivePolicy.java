package Advanced_Motor_Vehicle_System;
import java.time.LocalDate;

class ComprehensivePolicy extends InsurancePolicy {
    public ComprehensivePolicy(String policyId, Vehicle vehicle, Person policyHolder,
                               double coverageAmount, double premiumAmount,
                               LocalDate policyStartDate, LocalDate policyEndDate) {
        super(policyId, vehicle, policyHolder, coverageAmount, premiumAmount, policyStartDate, policyEndDate);
    }

    @Override
    double calculatePremium() {
        int vehicleAge = LocalDate.now().getYear() - vehicle.getVehicleYear();
        return premiumAmount + (vehicleAge * 50);
    }

    @Override
    boolean processClaim(double claimAmount) {
        return claimAmount <= coverageAmount;
    }

    @Override
    String generatePolicyReport() {
        return "Comprehensive Policy Report: Premium - " + premiumAmount + ", Coverage - " + coverageAmount;
    }

    @Override
    boolean validatePolicy() {
        return vehicle.getVehicleYear() > 2000;
    }
}

