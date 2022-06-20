package conveyor;

import DTO.LoanApplicationRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Offers {

/*    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return "Hello!";
    }
*/

    @PostMapping(value="/LoanApplicationRequestDTO", consumes="application/json", produces="application/json")
    public LoanApplicationRequestDTO createLoanApplicationRequestDTO (@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO){
        return loanApplicationRequestDTO;
    }
}
