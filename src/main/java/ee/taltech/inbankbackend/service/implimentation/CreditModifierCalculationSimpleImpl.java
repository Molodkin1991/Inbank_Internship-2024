package ee.taltech.inbankbackend.service.implimentation;

import ee.taltech.inbankbackend.config.DecisionEngineConstants;
import ee.taltech.inbankbackend.exceptions.NoValidLoanException;
import ee.taltech.inbankbackend.service.CreditModifierCalculation;
import org.springframework.stereotype.Component;

@Component
public class CreditModifierCalculationSimpleImpl implements CreditModifierCalculation {

    public int getCreditModifier(String personalCode) throws NoValidLoanException {

        /*
         * Calculates the credit modifier of the customer to according to the last four digits of their ID code.
         * Debt - 0000...2499
         * Segment 1 - 2500...4999
         * Segment 2 - 5000...7499
         * Segment 3 - 7500...9999
         *
         * @param personalCode ID code of the customer that made the request.
         * @return Segment to which the customer belongs.
         */

        int segment = Integer.parseInt(personalCode.substring(personalCode.length() - 4));

        if (segment < 2500) {
            // I would throw exception here instead of returning 0 score to be sure that there is no wierd cases with
            throw new NoValidLoanException("No valid loan found!");
        } else if (segment < 5000) {
            return DecisionEngineConstants.SEGMENT_1_CREDIT_MODIFIER;
        } else if (segment < 7500) {
            return DecisionEngineConstants.SEGMENT_2_CREDIT_MODIFIER;
        }

        return DecisionEngineConstants.SEGMENT_3_CREDIT_MODIFIER;
    }
}
