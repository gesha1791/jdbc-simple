package chaplinskiy.jdbccrud.util;

import java.util.Scanner;

public class ScannerSingleton {
    private static Scanner scanner;

    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void closeScanner() {
        try {
            scanner.close();
        } catch (Exception e){
            System.out.println("Scanner exception");
        }

    }
}
