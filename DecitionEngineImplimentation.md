# DecisionEngine implemented to provide decision on loan application based on personal code. 

A service class that provides a method for calculating an approved loan amount and period for a customer.
The loan amount is calculated based on the customer's credit modifier,
which is determined by the last four digits of their ID code and controller to provide answer to the frontend.
with following constrains:

- Minimum input and output sum can be 2000 €
- Maximum input and output sum can be 10000 €
- Minimum loan period can be 12 months
- Maximum loan period can be 60 months
- Scoring algorithm. For calculating credit score, a really primitive algorithm should be implemented.
- You need to divide the credit modifier with the loan amount and multiply the result with the loan
period in months.
- If the result is less than 1, then we would not approve such a sum.
- If the result is larger or equal than 1 then we would approve this sum.
- credit score = (credit modifier / loan amount) * loan period
- 
## Initial implementation for MVP solution `TICKET-101`

#### CalculateApprovedLoan method inside `DecisionEngine` class
#### Simple creditScore implementation based on personal code inside the DecisionClass
#### Modification of loan period based on provided criteria/constrains
#### Exceptions added to handle situation when inputs are invalid OR no loan is available 
#### JavaDoc done for all methods and classes

## Improvements made to original MVP solution `TICKET-101`

#### Replaced following iteration with formula:

`
while (highestValidLoanAmount(loanPeriod) < DecisionEngineConstants.MINIMUM_LOAN_AMOUNT) {
loanPeriod++;
}
`

Reasons:
1. Loop can potentially be endless ( if validLoanPeriod or credit score is 0)
2. No reason to do via iteration something that can be done via formula - it is simpler and potentially consumes less resources

####  CreditModifier var was declared as a  field, which would cause mixing of credit score between requests, so I moved it into the method that does the calculations

####  CreditModifier calculation is moved to separate Class to make solution easier to read and modify in future (following SOLID)

####  Instead of returning `0` value to indicate no credit score -> throwing exception, as a cleaner way to say there is no credit score.


#### Refactored to use interfaces instead of classes following SOLID