package DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ScoringDataDTO {
      public BigDecimal amount;
      public Integer term;
      public String firstName;
      public String lastName;
      public String middleName;
      public Enum gender;
      public LocalDate birthdate;
      public String passportSeries;
      public String passportNumber;
      public LocalDate passportIssueDate;
      public String passportIssueBranch;
      public MaritalStatus maritalStatus;
      public Integer dependentAmount;
      public EmploymentDTO employment;
      public String account;
      public Boolean isInsuranceEnabled;
      public Boolean isSalaryClient;

      public BigDecimal getAmount() {
            return this.amount;
      }

      public Integer getTerm() {
            return this.term;
      }

      public String getFirstName() {
            return this.firstName;
      }

      public String getLastName() {
            return this.lastName;
      }

      public String getMiddleName() {
            return this.middleName;
      }

      public Enum getGender() {
            return this.gender;
      }

      public LocalDate getBirthdate() {
            return this.birthdate;
      }

      public String getPassportSeries() {
            return this.passportSeries;
      }

      public String getPassportNumber() {
            return this.passportNumber;
      }

      public LocalDate getPassportIssueDate() {
            return this.passportIssueDate;
      }

      public String getPassportIssueBranch() {
            return this.passportIssueBranch;
      }

      public enum MaritalStatus {
            MARRIED, WIDOWED, SEPARATED, DIVORCED, SINGLE
      }

      public MaritalStatus getMaritalStatus() {
            return this.maritalStatus;
      }

      public Integer getDependentAmount() {
            return this.dependentAmount;
      }

      public EmploymentDTO getEmployment() {
            return this.employment;
      }

      public String getAccount() {
            return this.account;
      }

      public Boolean getIsInsuranceEnabled() {
            return this.isInsuranceEnabled;
      }

      public Boolean getIsSalaryClient() {
            return this.isSalaryClient;
      }
}
