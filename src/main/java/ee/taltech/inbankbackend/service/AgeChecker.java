package ee.taltech.inbankbackend.service;

import ee.taltech.inbankbackend.exceptions.InvalidPersonalCodeException;
import ee.taltech.inbankbackend.exceptions.NoValidLoanException;

public interface AgeChecker {
    void checkIfAgeIsWithinAcceptableRange(String personalCode) throws InvalidPersonalCodeException, NoValidLoanException;
}
