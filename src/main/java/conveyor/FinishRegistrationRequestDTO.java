package conveyor;

import java.time.LocalDate;

public class FinishRegistrationRequestDTO {
      private Enum gender;
      private Enum maritalStatus;
      private Integer dependentAmount;
      private LocalDate passportIssueDate;
      private String passportIssueBrach;
      private EmploymentDTO employment;
      private String account;
}
