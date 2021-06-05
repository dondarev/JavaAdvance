import service.Explorer;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String mainPath = "C:/BondarevDenis/JavaAdvance";
        System.out.println("\u0053\u0068\u006f\u0077\u0020\u0064\u0069\u0072\u0065\u0063\u0074\u006f\u0072\u0079\u003f\u0028\u0074\u0072\u0075\u0065\u0020\u006f\u0072\u0020\u0066\u0061\u006c\u0073\u0065\u0029");
        Scanner scaner = new Scanner(System.in);
        boolean showDirectory = scaner.nextBoolean();
        new Explorer(showDirectory,mainPath);
    }
}
