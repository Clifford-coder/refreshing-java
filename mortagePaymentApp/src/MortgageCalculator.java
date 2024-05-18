public class MortgageCalculator {
  private final byte NUM_OF_MONTHS_IN_A_YR = 12;
  private final byte PERCENT = 100;

  private int principal;
  private double compoundedRateFactor;
  private double monthlyInterestRate;
  private short numberOfPayments;

  public MortgageCalculator(int principal, float annualInterestRate, short periodInYears) {
    this.numberOfPayments = (short) (periodInYears * NUM_OF_MONTHS_IN_A_YR);
    this.monthlyInterestRate = (annualInterestRate / PERCENT) / NUM_OF_MONTHS_IN_A_YR;
    this.compoundedRateFactor = Math.pow(1 + monthlyInterestRate, numberOfPayments);
    this.principal = principal;
  }

  public double calculateMortgage()
      throws Exception {
    return (principal * monthlyInterestRate * compoundedRateFactor) / (compoundedRateFactor - 1);
  }

  public double calculateRemainingBalance(short numberOfPaymentsMade) {
    return (principal * (compoundedRateFactor
        - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade)))
        / (compoundedRateFactor - 1);
  }

  public double[] getRemainingBalances() {
    var balances = new double[numberOfPayments];
    for (short month = 1; month <= numberOfPayments; month++) {
      balances[month - 1] = calculateRemainingBalance(month);
    }
    return balances;
  }
}
