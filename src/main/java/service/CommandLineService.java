package service;

import dao.ContactDao;

import java.util.Scanner;

public class CommandLineService {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ContactService service = new ContactService(new ContactDao());
//    ContactService service = new ContactService();

    public static void showMenu() {
        System.out.println("1.Add contact.");
        System.out.println("2.Update contact.");
        System.out.println("3.Delete contact.");
        System.out.println("4.Show all contact.");
        System.out.println("0.Exit.;");
    }

    public static void run() {
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

                    break;
                }
                case 3: {

                    break;
                }
                case 4: {

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
