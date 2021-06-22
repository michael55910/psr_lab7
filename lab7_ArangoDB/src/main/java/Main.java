import config.Config;
import repository.CourseRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        new Config();
        Scanner scanner = new Scanner(System.in);
        CourseRepository courseRepository = new CourseRepository();

        //MENU
        Menu menu = new Menu();
        while (true) {
            int operation = menu.selectOperation();
            if (operation == 0) {
                return;
            }
            int target = menu.selectTarget();
            switch (operation) {
                case 1:
                    switch (target) {
                        case 1 -> courseRepository.add();
                        /*case 2 -> repairBookEntryRepository.addEntry();*/
                    }
                    break;
                case 2:
                    switch (target) {
                        case 1 -> courseRepository.updateById(getId());
                        /*case 2 -> repairBookEntryRepository.updateById(getId());*/
                    }
                    break;
                case 3:
                    switch (target) {
                        case 1 -> courseRepository.deleteById(getId());
                        /*case 2 -> repairBookEntryRepository.deleteById(getId());*/
                    }
                    break;
                case 4:
                    switch (target) {
                        case 1 -> courseRepository.getById(getId());
                        /*case 2 -> repairBookEntryRepository.getById(getId());*/
                    }
                    break;
                case 5:
                    switch (target) {
                        case 1 -> {
                            System.out.print("Enter destination city: ");
                            courseRepository.getByDestinationCity(scanner.next());
                        }
                        /*case 2 -> repairBookEntryRepository.getByDate();*/
                    }
                    break;
                case 6:
                    switch (target) {
                        case 1 -> {
                            System.out.print("Enter destination city: ");
                            courseRepository.getCountDestinationCity(scanner.next());
                        }
                    }
                    break;
                case 7:
                    switch (target) {
                        case 1 -> courseRepository.getAll();
                    }
                    break;
                default:
                    System.out.println("Błędny wybór!");
                    return;
            }
        }
    }

    private static Long getId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj id: ");
        return scanner.nextLong();
    }
}
