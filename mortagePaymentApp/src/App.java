import java.text.NumberFormat;
import java.util.Scanner;

public class App {
    final static byte NUM_OF_MONTHS_IN_A_YR = 12;
    final static byte PERCENT = 100;
    final static int MIN_PRINCIPAL = 1000;
    final static int MAX_PRINCIPAL = 1_000_000;
    final static byte MIN_ANNUAL_INTEREST_RATE = 0;
    final static byte MAX_ANNUAL_INTEREST_RATE = 30;
    final static byte MIN_PERIOD_IN_YEARS = 1;
    final static byte MAX_PERIOD_IN_YEARS = 30;

    public static void main(String[] args) throws Exception {

        try (Scanner scanner = new Scanner(System.in)) {
            int principal;
            float annualInterestRate;
            byte periodInYears;
            while (true) {
                System.out.print("Principal ($1K - $1M): ");
                principal = scanner.nextInt();

                if (principal < MIN_PRINCIPAL || principal > MAX_PRINCIPAL) {
                    System.out.println("Enter a number between 1,000 and 1,000,000");
                    continue;
                } else
                    break;
            }
            while (true) {
                System.out.print("Annual Interest Rate: ");
                annualInterestRate = scanner.nextFloat();
                if (annualInterestRate <= MIN_ANNUAL_INTEREST_RATE || annualInterestRate > MAX_ANNUAL_INTEREST_RATE) {
                    System.out.println("Enter a value greater than 0 and less than or equals to 30");
                    continue;
                } else
                    break;

            }
            while (true) {
                System.out.print("Period (Years): ");
                periodInYears = scanner.nextByte();
                if (periodInYears < MIN_PERIOD_IN_YEARS || periodInYears > MAX_PERIOD_IN_YEARS) {
                    System.out.println("Enter a value between 1 and 30");
                    continue;
                } else
                    break;
            }
            double mortgate = calculateMortgate(principal, annualInterestRate, periodInYears);
            String mortageFormatedInDollars = NumberFormat.getCurrencyInstance().format(mortgate);

            System.out.println("Your mortgage is : " + mortageFormatedInDollars);
        }
    }

    private static double calculateMortgate(int principal, float annualInterestRate, byte periodInYears)
            throws Exception {
        int numberOfPayments = periodInYears * NUM_OF_MONTHS_IN_A_YR;
        double monthlyInterestRate = (annualInterestRate / PERCENT) / NUM_OF_MONTHS_IN_A_YR;
        double exponentialAnnualRate = Math.pow((1 + monthlyInterestRate), numberOfPayments);
        double mortgate = (principal * monthlyInterestRate * exponentialAnnualRate) / (exponentialAnnualRate - 1);
        return mortgate;
    }
}
