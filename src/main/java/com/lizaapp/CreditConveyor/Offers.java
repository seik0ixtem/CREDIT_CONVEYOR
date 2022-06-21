package com.lizaapp.CreditConveyor;

import DTO.LoanApplicationRequestDTO;
import DTO.LoanOfferDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class Offers {

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value="/LoanOffer", consumes="application/json", produces="application/json")
    @ResponseBody
    public List<LoanOfferDTO> createLoanOfferDTO (
            @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO
    ) throws JsonProcessingException {
/*        ArrayList<LoanOfferDTO> offers = new ArrayList<LoanOfferDTO>();
        offers.add(new LoanOfferDTO());
        return offers;*/

        System.out.println(objectMapper.writeValueAsString(loanApplicationRequestDTO));
        return Collections.emptyList();
    }

    /*    @GetMapping("/getTest")
    public String getTest(){
        return "Hello, Elizabeth!";
    }
    */
}



