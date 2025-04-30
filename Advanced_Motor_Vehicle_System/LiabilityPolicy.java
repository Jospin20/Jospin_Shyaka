package Advanced_Motor_Vehicle_System;
import java.time.LocalDate;

class LiabilityPolicy extends InsurancePolicy {
    private boolean medicalCheckPassed;

    public LiabilityPolicy(String policyId, Vehicle vehicle, Person policyHolder,
                           double coverageAmount, double premiumAmount, boolean medicalCheckPassed,
                           LocalDate policyStartDate, LocalDate policyEndDate) {
        super(policyId, vehicle, policyHolder, coverageAmount, premiumAmount, policyStartDate, policyEndDate);
        this.medicalCheckPassed = medicalCheckPassed;
    }

    @Override
    double calculatePremium() {
        return medicalCheckPassed ? premiumAmount * 0.95 : premiumAmount;
    }

    @Override
    boolean processClaim(double claimAmount) {
        return claimAmount <= coverageAmount;
    }

    @Override
    String generatePolicyReport() {
        return "Liability Policy Report: Premium - " + premiumAmount + ", Coverage - " + coverageAmount;
    }

    @Override
    boolean validatePolicy() {
        return medicalCheckPassed;
    }
}
