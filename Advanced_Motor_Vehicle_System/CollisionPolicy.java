package Advanced_Motor_Vehicle_System;
import java.time.LocalDate;

class CollisionPolicy extends InsurancePolicy {
    private boolean safetyCheckPassed;

    public CollisionPolicy(String policyId, Vehicle vehicle, Person policyHolder,
                           double coverageAmount, double premiumAmount, boolean safetyCheckPassed,
                           LocalDate policyStartDate, LocalDate policyEndDate) {
        super(policyId, vehicle, policyHolder, coverageAmount, premiumAmount, policyStartDate, policyEndDate);
        this.safetyCheckPassed = safetyCheckPassed;
    }

    @Override
    double calculatePremium() {
        return safetyCheckPassed ? premiumAmount * 0.9 : premiumAmount;
    }

    @Override
    boolean processClaim(double claimAmount) {
        return claimAmount <= coverageAmount;
    }

    @Override
    String generatePolicyReport() {
        return "Collision Policy Report: Premium - " + premiumAmount + ", Coverage - " + coverageAmount;
    }

    @Override
    boolean validatePolicy() {
        return safetyCheckPassed;
    }
}

