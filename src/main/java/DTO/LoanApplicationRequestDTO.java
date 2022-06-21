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
    //todo: fix
    //public LocalDate birthdate;
    public String passportSeries;
    public String passportNumber;
}

