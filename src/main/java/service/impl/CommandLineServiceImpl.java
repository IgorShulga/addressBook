package service.impl;

import dao.impl.ContactDaoImpl;
import service.CommandLIneService;

import java.util.Scanner;

public class CommandLineServiceImpl implements CommandLIneService {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ContactServiceImpl service = new ContactServiceImpl(new ContactDaoImpl());

    public void showMenu() {
        System.out.println("1.Add contact.");
        System.out.println("2.Update contact.");
        System.out.println("3.Delete contact.");
        System.out.println("4.Show all contact.");
        System.out.println("0.Exit.;");
    }

    public void start() {
//        run();
    }

    public void run() {
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
