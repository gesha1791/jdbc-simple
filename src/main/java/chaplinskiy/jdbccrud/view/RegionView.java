package chaplinskiy.jdbccrud.view;

import chaplinskiy.jdbccrud.controller.RegionController;
import chaplinskiy.jdbccrud.model.Region;

import java.util.List;
import java.util.Scanner;

import static chaplinskiy.jdbccrud.util.Constants.*;
import static chaplinskiy.jdbccrud.util.PrintUtils.printMessage;
import static chaplinskiy.jdbccrud.util.ScannerSingleton.getScanner;

public class RegionView {
    private final Scanner scanner;
    private final RegionController regionController;


    public RegionView() {
        scanner = getScanner();
        regionController = new RegionController();
    }

    public void run() {
        boolean start = true;
        while (start) {
            printMessage(regionViewMessage);
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    List<Region> allRegions = regionController.getAllRegions();

                    allRegions.stream().forEach(region -> {
                        System.out.println("id: " + region.getId() + ", name: " + region.getName() + "\n");
                    });
                    break;
                case 2:
                    printMessage(createUpdateRegionNameMessage);
                    String name = scanner.next();
                    regionController.createRegion(name);
                    break;
                case 3:
                    printMessage(idRegionMessage);
                    Long idDelete = Long.valueOf(scanner.nextInt());
                    regionController.deleteRegionById(idDelete);
                    break;
                case 4:
                    printMessage(idRegionMessage);
                    long idUpdate = scanner.nextLong();
                    printMessage(createUpdateRegionNameMessage);
                    String nameUpdateRegion = scanner.next();
                    Region region = new Region(idUpdate, nameUpdateRegion);
                    regionController.updateRegion(region);
                    break;
                case 5:
                    printMessage(idRegionMessage);
                    Long id = Long.valueOf(scanner.nextInt());
                    Region regionById = regionController.getRegionById(id);

                    System.out.println("id: " + regionById.getId() + ", name: " + regionById.getName() + "\n");

                    break;
                case 6:
                    start = false;
                    break;
                default:
                    printMessage(wrongRegionMessage);
            }
        }
    }
}
