package com.lizaapp.CreditConveyor;

import DTO.LoanApplicationRequestDTO;
import DTO.LoanOfferDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LoanService {

    @Value("${BaseRate}")
    BigDecimal baseRate;

    public List<LoanOfferDTO> prescore(LoanApplicationRequestDTO loanApplicationRequestDTO) throws Exception {

        //ПРЕСКОРИНГ

        Pattern namePattern = Pattern.compile("^[a-zA-Z]{2,30}$");
        Pattern pasSeriesPattern = Pattern.compile("\\d");
        Pattern birthdatePattern = Pattern.compile("(19|20)\\d\\d-((0[1-9]|1[012])-(0[1-9]|[12]\\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)");
        Pattern emailPattern = Pattern.compile("[\\w\\.]{2,50}@[\\w\\.]{2,20}");

        String firstName = loanApplicationRequestDTO.getFirstName();
        Matcher fNameMatcher = namePattern.matcher(firstName);

        String lastName = loanApplicationRequestDTO.getLastName();
        Matcher lNameMatcher = namePattern.matcher(lastName);
        System.out.println(lNameMatcher.find());

        String middleName = loanApplicationRequestDTO.getMiddleName();
        Matcher mNameMatcher = namePattern.matcher(middleName);

        String passportSeries = loanApplicationRequestDTO.getPassportSeries();
        Matcher pasSeriesMatcher = pasSeriesPattern.matcher(passportSeries);

        String passportNumber = loanApplicationRequestDTO.getPassportNumber();
        Matcher pasNumberMatcher = pasSeriesPattern.matcher(passportNumber);

        LocalDate birthDate = loanApplicationRequestDTO.getBirthdate();
        Matcher birthdateMatcher = birthdatePattern.matcher(birthDate.toString());

        String email = loanApplicationRequestDTO.getEmail();
        Matcher emailMatcher = emailPattern.matcher(email);

        String namesCheckRegex = "^[a-zA-Z]{2,30}$";

        if (!firstName.matches(namesCheckRegex)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Введите корректное имя!", null);
        }

        if (!lastName.matches(namesCheckRegex)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Введите корректную фамилию!", null);
        }

        if (!Objects.equals(loanApplicationRequestDTO.getMiddleName(), "") & mNameMatcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Введите корректное отчество!", null);
        }

        if (loanApplicationRequestDTO.getAmount().compareTo(BigDecimal.valueOf(10000)) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Введите сумму больше или равную 10000!", null);
        }

        if (loanApplicationRequestDTO.getTerm() < 6) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Введите срок кредита больше или равный 6 месяцам!", null);
        }

        if (loanApplicationRequestDTO.getPassportSeries().length() != 4 ||
                !pasSeriesMatcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Введите корректную серию паспорта!", null);
        }

        if (loanApplicationRequestDTO.getPassportNumber().length() != 6 ||
                !pasNumberMatcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Введите корректный номер паспорта!", null);
        }

        if (!birthdateMatcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Введите корректную дату рождения!", null);
        }

        long fullYears = ChronoUnit.YEARS.between(loanApplicationRequestDTO.birthdate, LocalDate.now());
        if (fullYears < 18) {
            throw new Exception("Вы ещё слишком молоды для получения кредита.");
        }

        if (!emailMatcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Введите корректный адрес электронной почты!", null);
        }

        //РАСЧЁТ КРЕДИТА

        BigDecimal insurance = loanApplicationRequestDTO.getAmount().multiply(BigDecimal.valueOf(0.03));
        System.out.println(insurance);

        double baseRateP = baseRate.doubleValue(); //базовая ставка в процентах
        System.out.println(baseRateP);

        double rateWithInsuranceOrSalary = baseRate.doubleValue() - 1; //ставка со страховкой или ЗП
        System.out.println(rateWithInsuranceOrSalary);

        double rateWithInsuranceAndSalary = baseRate.doubleValue() - 2; //ставка со страховкой и ЗП
        System.out.println(rateWithInsuranceAndSalary);

        double monthRate1 = (baseRateP / 100) / 12; //месячная ставка по кредиту не в процентах
        System.out.println(monthRate1);

        double monthRate2 = (rateWithInsuranceOrSalary / 100) / 12; //месячная ставка по кредиту не в процентах с ЗП
        System.out.println(monthRate2);

        double monthRate3 = (rateWithInsuranceAndSalary / 100) / 12; //месячная ставка по кредиту не в процентах со страховкой и ЗП
        System.out.println(monthRate3);

        double annuityRatio1 = (monthRate1 * Math.pow((1 + monthRate1),
                loanApplicationRequestDTO.getTerm())) / (Math.pow((1 + monthRate1),
                loanApplicationRequestDTO.getTerm()) - 1); //коэффициент аннуитета без страховки и зарплаты
        System.out.println(annuityRatio1);

        double annuityRatio2 = (monthRate2 * Math.pow((1 + monthRate2),
                loanApplicationRequestDTO.getTerm())) / (Math.pow((1 + monthRate2),
                loanApplicationRequestDTO.getTerm()) - 1); //коэффициент аннуитета со страховкой или ЗП

        double annuityRatio4 = (monthRate3 * Math.pow((1 + monthRate3),
                loanApplicationRequestDTO.getTerm())) / (Math.pow((1 + monthRate3),
                loanApplicationRequestDTO.getTerm()) - 1); //коэффициент аннуитета со страховкой и ЗП

        BigDecimal monthlyPayment1 =
                loanApplicationRequestDTO.amount.multiply(new BigDecimal(annuityRatio1)).setScale(2, RoundingMode.HALF_UP);

        BigDecimal monthlyPayment2 =
                loanApplicationRequestDTO.amount.multiply(new BigDecimal(annuityRatio2)).setScale(2, RoundingMode.HALF_UP);

        BigDecimal monthlyPayment3 =
                (loanApplicationRequestDTO.amount.add(insurance)).multiply(new BigDecimal
                        (annuityRatio2)).setScale(2, RoundingMode.HALF_UP);

        BigDecimal monthlyPayment4 =
                (loanApplicationRequestDTO.amount.add(insurance)).multiply(new BigDecimal
                        (annuityRatio4)).setScale(2, RoundingMode.HALF_UP);
//
//        return List.of(
//                makePreoffer(loanApplicationRequestDTO, true, true),
//                makePreoffer(loanApplicationRequestDTO, true, false),
//                makePreoffer(loanApplicationRequestDTO, false, true),
//                makePreoffer(loanApplicationRequestDTO, false, false)
//        );
//
//
        return List.of(
                new LoanOfferDTO(
                        10L,
                        loanApplicationRequestDTO.getAmount(),
                        loanApplicationRequestDTO.getAmount(),
                        loanApplicationRequestDTO.getTerm(),
                        monthlyPayment1,
                        baseRate,
                        false,
                        false),
                new LoanOfferDTO(
                        20L,
                        loanApplicationRequestDTO.getAmount(),
                        loanApplicationRequestDTO.getAmount(),
                        loanApplicationRequestDTO.getTerm(),
                        monthlyPayment2,
                        new BigDecimal(rateWithInsuranceOrSalary),
                        false,
                        true),
                new LoanOfferDTO(
                        30L,
                        loanApplicationRequestDTO.getAmount(),
                        loanApplicationRequestDTO.getAmount().add(insurance),
                        loanApplicationRequestDTO.getTerm(),
                        monthlyPayment3,
                        new BigDecimal(rateWithInsuranceOrSalary),
                        true,
                        false),
                new LoanOfferDTO(
                        40L,
                        loanApplicationRequestDTO.getAmount(),
                        loanApplicationRequestDTO.getAmount().add(insurance),
                        loanApplicationRequestDTO.getTerm(),
                        monthlyPayment4,
                        new BigDecimal(rateWithInsuranceAndSalary),
                        true,
                        true)
        );
    }

    private LoanOfferDTO makePreoffer(
            LoanApplicationRequestDTO loanRequest,
            boolean isInsuranceEnabled,
            boolean isSalaryClient) {

        if (isInsuranceEnabled) {
            if (isSalaryClient) {
                return new LoanOfferDTO( ... );
            } else {
                return new LoanOfferDTO( ... );
            }
        } else if (isSalaryClient) {
            return new LoanOfferDTO( ... );
        } else {
            return new LoanOfferDTO( ... );
        }
    }
}