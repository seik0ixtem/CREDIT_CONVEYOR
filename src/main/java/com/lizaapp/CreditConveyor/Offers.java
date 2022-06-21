package com.lizaapp.CreditConveyor;

import DTO.LoanApplicationRequestDTO;
import DTO.LoanOfferDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class Offers {

    @PostMapping(value="/LoanOffer", consumes="application/json", produces="application/json")
    @ResponseBody
    public List<LoanOfferDTO> createLoanOfferDTO (@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO){
/*        ArrayList<LoanOfferDTO> offers = new ArrayList<LoanOfferDTO>();
        offers.add(new LoanOfferDTO());
        return offers;*/
        System.out.println((loanApplicationRequestDTO));
        return Collections.emptyList();
    }

    /*    @GetMapping("/getTest")
    public String getTest(){
        return "Hello, Elizabeth!";
    }
    */
}



