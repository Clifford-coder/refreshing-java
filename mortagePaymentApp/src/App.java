public class App {

    public static void main(String[] args) throws Exception {
        int principal = (int) NumberInputReader.prompt("Principal: ", 1000, 1_000_000);
        float annualInterestRate = (float) NumberInputReader.prompt("Annual Interest Rate: ", 0, 30);
        short periodInYears = (short) NumberInputReader.prompt("Period (Years): ", 1, 30);

        var calculator = new MortgageCalculator(principal, annualInterestRate, periodInYears);
        var report = new MortgageReport(calculator);
        report.printMortgage();
        report.printPaymentSchedule();
    }
}
