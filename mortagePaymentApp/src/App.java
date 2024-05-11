import java.text.NumberFormat;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        final byte NUM_OF_MONTHS_IN_A_YR = 12;
        final byte PERCENT = 100;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Principal: ");
            int principal = scanner.nextInt();

            System.out.print("Annual Interest Rate: ");
            float annualInterestRate = scanner.nextFloat();

            System.out.print("Period (Years): ");
            int periodInYears = scanner.nextInt();

            int numberOfPayments = periodInYears * NUM_OF_MONTHS_IN_A_YR;
            double monthlyInterestRate = (annualInterestRate / PERCENT) / NUM_OF_MONTHS_IN_A_YR;
            double mortgate = calculateMortgate(principal, monthlyInterestRate, numberOfPayments);
            String mortageFormatedInDollars = NumberFormat.getCurrencyInstance().format(mortgate);

            System.out.println("Your mortgage is : " + mortageFormatedInDollars);
        }
    }

    private static double calculateMortgate(int principal, double monthlyInterestRate, int numberOfPayments)
            throws Exception {
        double exponentialAnnualRate = Math.pow((1 + monthlyInterestRate), numberOfPayments);
        double mortgate = (principal * monthlyInterestRate * exponentialAnnualRate) / (exponentialAnnualRate - 1);
        return mortgate;
    }
}
