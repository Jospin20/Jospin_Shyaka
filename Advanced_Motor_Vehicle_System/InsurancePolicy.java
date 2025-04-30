package Advanced_Motor_Vehicle_System;

import java.time.LocalDate;

abstract class InsurancePolicy {
    protected String policyId;
    protected Vehicle vehicle;
    protected Person policyHolder;
    protected double coverageAmount;
    protected double premiumAmount;
    protected LocalDate policyStartDate;
    protected LocalDate policyEndDate;

    public InsurancePolicy(String policyId, Vehicle vehicle, Person policyHolder,
                           double coverageAmount, double premiumAmount,
                           LocalDate policyStartDate, LocalDate policyEndDate) {
        this.policyId = policyId;
        this.vehicle = vehicle;
        this.policyHolder = policyHolder;
        this.coverageAmount = coverageAmount;
        this.premiumAmount = premiumAmount;
        this.policyStartDate = policyStartDate;
        this.policyEndDate = policyEndDate;
    }

    abstract double calculatePremium();
    abstract boolean processClaim(double claimAmount);
    abstract String generatePolicyReport();
    abstract boolean validatePolicy();
}
