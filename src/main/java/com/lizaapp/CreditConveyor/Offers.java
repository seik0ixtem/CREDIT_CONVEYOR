package com.lizaapp.CreditConveyor;

import DTO.LoanApplicationRequestDTO;
import DTO.LoanOfferDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class Offers {

    @Autowired private ObjectMapper objectMapper;
    @Autowired private LoanService loanService;

    @PostMapping(value="/conveyor/offers", consumes="application/json", produces="application/json")
    @ResponseBody
    public List<LoanOfferDTO> createLoanOfferDTO (
            @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO
    ) throws Exception {
        System.out.println(objectMapper.writeValueAsString(loanApplicationRequestDTO));

        return loanService.prescore(loanApplicationRequestDTO);
    }
}



