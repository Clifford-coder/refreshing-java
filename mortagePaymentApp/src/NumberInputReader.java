import java.util.Scanner;

public class NumberInputReader {
  public static double prompt(String prompt, int min, int max) {
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

}
