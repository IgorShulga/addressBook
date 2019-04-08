package service;

import service.impl.ContactServiceImpl;

import java.util.Scanner;

public interface CommandLIneService {

    /**
     * Static method with its implementation
     * method displayed actions menu.
     */
    static void showMenu() {
        System.out.println("1.Add contact.");
        System.out.println("2.Update contact.");
        System.out.println("3.Delete contact.");
        System.out.println("4.Show all contact.");
        System.out.println("0.Exit.;");
    }


    /**
     * Static method with its implementation
     * it is start work application
     *
     * @param scanner takes keyboard input
     * @param service pass scanner for implementation
     */
    static void run(Scanner scanner, ContactServiceImpl service) {
        boolean exit = true;
        do {
            System.out.println("Choose your wish:");
            showMenu();
            int numberOfMenu = scanner.nextInt();
            switch (numberOfMenu) {
                case 1: {
                    service.addContact(scanner);
                    break;
                }
                case 2: {
                    service.updateContact(scanner);
                    break;
                }
                case 3: {
                    service.deleteContact(scanner);
                    break;
                }
                case 4: {
                    service.showAllContacts(scanner);
                    break;
                }
                case 0: {
                    System.out.println("Thank you that use our app. Good bay.");
                    exit = false;
                    break;
                }
                default: {
                    System.out.println("Sorry. You enter wrong number of menu. Choose another number.");
                }
            }
        } while (exit);
    }

}
