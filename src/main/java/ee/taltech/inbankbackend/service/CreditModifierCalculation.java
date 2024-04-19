package ee.taltech.inbankbackend.service;

import ee.taltech.inbankbackend.exceptions.NoValidLoanException;

public interface CreditModifierCalculation {
    int getCreditModifier(String personalCode) throws NoValidLoanException;
}
