import java.text.NumberFormat;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        final byte NUM_OF_MONTHS_IN_A_YR = 12;
        final byte PERCENT = 100;
        int principal = (int) readNumber("Principal: ", 1000, 1_000_000);
        float annualInterestRate = (float) readNumber("Annual Interest Rate: ", 0, 30);
        byte periodInYears = (byte) readNumber("Period (Years): ", 1, 30);
        short numberOfPayments = (short) (periodInYears * NUM_OF_MONTHS_IN_A_YR);
        double monthlyInterestRate = (annualInterestRate / PERCENT) / NUM_OF_MONTHS_IN_A_YR;
        double compoundedRateFactor = Math.pow(1 + monthlyInterestRate, numberOfPayments);
        printMortgage(principal, monthlyInterestRate, compoundedRateFactor);
        printPaymentSchedule(principal, numberOfPayments, monthlyInterestRate, compoundedRateFactor);
    }

    private static double readNumber(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value < min || value > max) {
                System.out.println("Enter a number between " + min + " and " + max);
                continue;
            } else
                break;
        }

        return value;
    }

    private static void printMortgage(int principal, double monthlyInterestRate,
            double compoundedRateFactor) throws Exception {
        double mortgage = calculateMortgage(principal, monthlyInterestRate, compoundedRateFactor);
        String mortgageFormattedInDollars = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("MORTGAGE");
        System.out.println("-------------");
        System.out.println("Monthly Payments: " + mortgageFormattedInDollars);
    }

    private static void printPaymentSchedule(int principal, short numberOfPayments, double monthlyInterestRate,
            double compoundedRateFactor) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("-------------------");
        for (short month = 1; month <= numberOfPayments; month++) {
            double remainingBalance = calculateRemainingBalance(principal, monthlyInterestRate, compoundedRateFactor,
                    month);
            String mortgageRemainingBalInDollars = NumberFormat.getCurrencyInstance().format(remainingBalance);
            System.out.print("Month " + month + "=> ");
            System.out.println(mortgageRemainingBalInDollars);
        }
    }

    private static double calculateMortgage(int principal, double monthlyInterestRate, double compoundedRateFactor)
            throws Exception {
        return (principal * monthlyInterestRate * compoundedRateFactor) / (compoundedRateFactor - 1);
    }

    private static double calculateRemainingBalance(int principal, double monthlyInterestRate,
            double compoundedRateFactor, short numberOfPaymentsMade)  {
        return (principal * (compoundedRateFactor
                - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade)))
                / (compoundedRateFactor - 1);
    }

}
