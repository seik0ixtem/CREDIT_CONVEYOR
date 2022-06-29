package DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanApplicationRequestDTO {
    public BigDecimal amount;
    public Integer term;
    public String firstName;
    public String lastName;
    public String middleName;
    public String email;
    public LocalDate birthdate;
    public String passportSeries;
    public String passportNumber;

    public BigDecimal getAmount() {
        return this.amount;
    }
    public Integer getTerm() {
        return this.term;
    }
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    public String getMiddleName(){return this.middleName;}
    public String getEmail(){return this.email;}
    public LocalDate getBirthdate(){return this.birthdate;}
    public String getPassportSeries() {return this.passportSeries;}
    public String getPassportNumber(){return this.passportNumber;}
}

