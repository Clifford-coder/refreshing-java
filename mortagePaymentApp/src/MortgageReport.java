import java.text.NumberFormat;

public class MortgageReport {
  private MortgageCalculator calculator;
  private NumberFormat currency;

  public MortgageReport(MortgageCalculator calculator) {
    this.calculator = calculator;
    currency = NumberFormat.getCurrencyInstance();
  }

  public void printMortgage() throws Exception {
    double mortgage = calculator.calculateMortgage();
    String mortgageFormattedInDollars = currency.format(mortgage);
    System.out.println("MORTGAGE");
    System.out.println("-------------");
    System.out.println("Monthly Payments: " + mortgageFormattedInDollars);
  }

  public void printPaymentSchedule() {
    System.out.println();
    System.out.println("PAYMENT SCHEDULE");
    System.out.println("-------------------");
    for (double balance : calculator.getRemainingBalances()) {
      String remainingBalInDollars = currency.format(balance);
      System.out.println(remainingBalInDollars);
    }
  }
}
