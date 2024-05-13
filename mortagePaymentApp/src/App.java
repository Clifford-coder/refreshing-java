import java.text.NumberFormat;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        int principal = (int) readNumber("Principal: ", 1000, 1_000_000);
        float annualInterestRate = (float) readNumber("Annual Interest Rate: ", 0, 30);
        byte periodInYears = (byte) readNumber("Period (Years): ", 1, 30);
        double mortgate = calculateMortgate(principal, annualInterestRate, periodInYears);
        String mortageFormatedInDollars = NumberFormat.getCurrencyInstance().format(mortgate);

        System.out.println("Your mortgage is : " + mortageFormatedInDollars);
    }

    private static double calculateMortgate(int principal, float annualInterestRate, byte periodInYears)
            throws Exception {
        final byte NUM_OF_MONTHS_IN_A_YR = 12;
        final byte PERCENT = 100;
        short numberOfPayments = (short) (periodInYears * NUM_OF_MONTHS_IN_A_YR);
        double monthlyInterestRate = (annualInterestRate / PERCENT) / NUM_OF_MONTHS_IN_A_YR;
        double exponentialAnnualRate = Math.pow((1 + monthlyInterestRate), numberOfPayments);
        double mortgate = (principal * monthlyInterestRate * exponentialAnnualRate) / (exponentialAnnualRate - 1);
        return mortgate;
    }

    private static double readNumber(String prompt, int min, int max) {
        try (Scanner scanner = new Scanner(System.in)) {
            double value;
            while (true) {
                System.out.print(prompt);
                value = scanner.nextInt();
                if (value < min || value > max) {
                    System.out.println("Enter a number between " + min + "and " + max);
                    continue;
                } else
                    break;
            }

            return value;
        }
    }
}
