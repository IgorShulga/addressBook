package service;

import constants.MassageApp;
import exception.ApplicationException;
import constants.ResponseCode;
import service.impl.CommandLineServiceImpl;
import service.impl.ContactServiceImpl;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Scanner;

public interface CommandLIneService {

    /**
     * Method displayed actions menu.
     */
    static void showMenu() {
        System.out.println("1.Add contact.");
        System.out.println("2.Update contact. ");
        System.out.println("3.Delete contact.");
        System.out.println("4.Show all contact.");
        System.out.println("0.Exit.;");
    }


    /**
     * Method is start work application
     *
     * @param scanner takes keyboard input
     * @param service pass scanner for implementation
     */
    static void run(Scanner scanner, ContactServiceImpl service) throws ApplicationException {
        int numberOfMenu;
        boolean exit = true;
        do {
            System.out.println("Choose action: ");
            showMenu();

            try {
                String str = scanner.next();
                if (isCorrectData(str)) {
                    numberOfMenu = Integer.parseInt(str);
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
                            System.out.println("Thank you that used our app. Good bay.");
                            exit = false;
                            break;
                        }
                        default: {
                            System.out.println("You entered invalid number. Repeat please.");
                        }
                    }
                }
            } catch (ApplicationException e) {
                System.out.println("Exception: " + e.getCode().getCode());
            }
        }
        while (exit);
    }

    static boolean isCorrectData(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println(ResponseCode.WRONG_DATA_TYPE + " " + MassageApp.DATA_TYPE_IS_NOT_NUMBER);
            return false;
        }
        return true;
    }
}

