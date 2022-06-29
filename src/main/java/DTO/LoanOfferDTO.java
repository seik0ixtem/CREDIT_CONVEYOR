package DTO;

import java.math.BigDecimal;

public class LoanOfferDTO {
    public Long applicationId;
    public BigDecimal requestedAmount;
    public BigDecimal totalAmount;
    public Integer term;
    public BigDecimal monthlyPayment;
    public BigDecimal rate;
    public Boolean isInsuranceEnabled;
    public Boolean isSalaryClient;

    public LoanOfferDTO(
            Long applicationId,
            BigDecimal requestedAmount,
            BigDecimal totalAmount,
            Integer term,
            BigDecimal monthlyPayment,
            BigDecimal rate,
            Boolean isInsuranceEnabled,
            Boolean isSalaryClient) {
        this.applicationId = applicationId;
        this.requestedAmount = requestedAmount;
        this.totalAmount = totalAmount;
        this.term = term;
        this.monthlyPayment = monthlyPayment;
        this.rate = rate;
        this.isInsuranceEnabled = isInsuranceEnabled;
        this.isSalaryClient = isSalaryClient;
    }
}
