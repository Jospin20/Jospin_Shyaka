package Advanced_Motor_Vehicle_System;
import java.time.LocalDate;

class RoadsideAssistancePolicy extends InsurancePolicy {
    private boolean registrationVerified;

    public RoadsideAssistancePolicy(String policyId, Vehicle vehicle, Person policyHolder,
                                    double coverageAmount, double premiumAmount, boolean registrationVerified,
                                    LocalDate policyStartDate, LocalDate policyEndDate) {
        super(policyId, vehicle, policyHolder, coverageAmount, premiumAmount, policyStartDate, policyEndDate);
        this.registrationVerified = registrationVerified;
    }

    @Override
    double calculatePremium() {
        return registrationVerified ? premiumAmount : premiumAmount * 1.1;
    }

    @Override
    boolean processClaim(double claimAmount) {
        return claimAmount <= coverageAmount;
    }

    @Override
    String generatePolicyReport() {
        return "Roadside Assistance Policy Report: Premium - " + premiumAmount + ", Coverage - " + coverageAmount;
    }

    @Override
    boolean validatePolicy() {
        return registrationVerified;
    }
}

