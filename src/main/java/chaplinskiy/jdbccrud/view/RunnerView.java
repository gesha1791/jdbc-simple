package chaplinskiy.jdbccrud.view;

import java.util.Scanner;

import static chaplinskiy.jdbccrud.util.Constants.runnerViewMessage;
import static chaplinskiy.jdbccrud.util.Constants.wrongRunnerMessage;
import static chaplinskiy.jdbccrud.util.PrintUtils.printMessage;
import static chaplinskiy.jdbccrud.util.ScannerSingleton.closeScanner;
import static chaplinskiy.jdbccrud.util.ScannerSingleton.getScanner;

public class RunnerView {
    private static RunnerView view;
    private RegionView regionView;
    private PostView postView;
    private UserView userView;

    private final Scanner scanner;
    boolean isActive = true;

    public RunnerView() {
        regionView = new RegionView();
        postView = new PostView();
        userView = new UserView();
        scanner = getScanner();
    }

    public static RunnerView getInstance() {
        if (view == null) {
            view = new RunnerView();
        }
        return view;
    }

    public void run() {
        while (isActive) {
            printMessage(runnerViewMessage);

            int number = scanner.nextInt();

            switch (number) {
                case 1:
                    userView.run();
                    break;
                case 2:
                    postView.run();
                    break;
                case 3:
                    regionView.run();
                    break;
                case 4:
                    try {
                        closeScanner();
                    } catch (Exception e){
                        System.out.println(e);
                    }

                    System.exit(0);
                default:
                    printMessage(wrongRunnerMessage);
            }
        }
    }
}
