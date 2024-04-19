package ee.taltech.inbankbackend.service.implimentation;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.locale.estonia.EstonianPersonalCodeParser;
import com.github.vladislavgoltjajev.personalcode.locale.estonia.EstonianPersonalCodeValidator;
import ee.taltech.inbankbackend.exceptions.*;
import ee.taltech.inbankbackend.service.AgeChecker;
import org.springframework.stereotype.Service;

import java.time.Period;

import static ee.taltech.inbankbackend.config.DecisionEngineConstants.AVERAGE_LIFE_EXPECTANCY;
import static ee.taltech.inbankbackend.config.DecisionEngineConstants.MAXIMUM_LOAN_PERIOD;

@Service
public class BalticAgeChecker implements AgeChecker {

    private final InvalidPersonalCodeException INVALID_PERSONAL_CODE_EXCEPTION = new InvalidPersonalCodeException("Invalid personal ID code!");
    private final EstonianPersonalCodeValidator personalCodeValidator = new EstonianPersonalCodeValidator();
    private final EstonianPersonalCodeParser personalCodeParser = new EstonianPersonalCodeParser();

    public void checkIfAgeIsWithinAcceptableRange(String personalCode) throws InvalidPersonalCodeException, NoValidLoanException {
        if (!personalCodeValidator.isValid(personalCode)) {
            throw INVALID_PERSONAL_CODE_EXCEPTION;
        }

        try {
            Period age = personalCodeParser.getAge(personalCode);

            if (age.getYears() < 18) {
                throw new NoValidLoanException("Too young to get a loan!");
            } else if (age.getMonths() > (AVERAGE_LIFE_EXPECTANCY.getMonths() - MAXIMUM_LOAN_PERIOD)) {
                throw new NoValidLoanException("Age is above average life expectancy!");
            }

        } catch (PersonalCodeException e) {
            throw INVALID_PERSONAL_CODE_EXCEPTION;
        }
    }

}
