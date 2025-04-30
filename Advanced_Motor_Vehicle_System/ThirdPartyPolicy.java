package Advanced_Motor_Vehicle_System;
import java.time.LocalDate;

class ThirdPartyPolicy extends InsurancePolicy {
    private double engineCapacity;

    public ThirdPartyPolicy(String policyId, Vehicle vehicle, Person policyHolder,
                            double coverageAmount, double premiumAmount, double engineCapacity,
                            LocalDate policyStartDate, LocalDate policyEndDate) {
        super(policyId, vehicle, policyHolder, coverageAmount, premiumAmount, policyStartDate, policyEndDate);
        this.engineCapacity = engineCapacity;
    }

    @Override
    double calculatePremium() {
        return premiumAmount + (engineCapacity * 20);
    }

    @Override
    boolean processClaim(double claimAmount) {
        return claimAmount <= coverageAmount;
    }

    @Override
    String generatePolicyReport() {
        return "Third-Party Policy Report: Premium - " + premiumAmount + ", Coverage - " + coverageAmount;
    }

    @Override
    boolean validatePolicy() {
        return engineCapacity > 0;
    }
}
