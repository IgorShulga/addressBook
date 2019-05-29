package service;

import constants.MassageApp;
import exception.ApplicationException;
import constants.ResponseCode;
import service.impl.ContactServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;

public interface CommandLIneService {

    String JDBC = "jdbc:mysql:";
    String HOST_AND_PORT = "//localhost:3306";
    String NAME_DB = "/address_book";
    String UNICODE = "useUnicode=true";
    String JDBC_TIME_ZONE_SHIFT = "useJDBCCompliantTimezoneShift=true";
    String DATE_TIME = "useLegacyDatetimeCode=false";
    String SERVICE_TOME_ZONE = "serverTimezone=UTC";
    String SEPARATOR = "?";
    String SEPARATOR_AND = "&";
    String FULL_URL = JDBC + HOST_AND_PORT + NAME_DB + SEPARATOR +
            UNICODE + SEPARATOR_AND + JDBC_TIME_ZONE_SHIFT +
            SEPARATOR_AND + DATE_TIME +SEPARATOR_AND + SERVICE_TOME_ZONE;
    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS contacts(" +
            "  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
            "  `name` VARCHAR(255) NOT NULL, " +
            "  `sur_name` VARCHAR(255) NULL, " +
            "  `phone_number` VARCHAR(12) NOT NULL, " +
            "  `age` INT NULL, " +
            "  `haight` FLOAT NULL, " +
            "  `married` BOOLEAN DEFAULT false, " +
            "  `create_data_time` TIMESTAMP)";
    String USER = "root";
    String PASSWORD = "root";
    String INSERT_CONTACT = "insert into person (name,age,married) VALUES (?,?,?)";

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
     * @param readerKeyboard takes keyboard input
     * @param service        pass readerKeyboard for implementation
     */
    static void run(BufferedReader readerKeyboard, ContactServiceImpl service) {

        int numberOfMenu;
        boolean exit = true;
        do {
            System.out.println("Choose action: ");
            showMenu();

            try {
                String stringTemp = readerKeyboard.readLine();
                if (isCorrectInteger(stringTemp)) {
                    numberOfMenu = Integer.parseInt(stringTemp);
                    switch (numberOfMenu) {
                        case 1: {
                            service.addContact(readerKeyboard);
                            break;
                        }
                        case 2: {
                            service.updateContact(readerKeyboard);
                            break;
                        }
                        case 3: {
                            service.deleteContact(readerKeyboard);
                            break;
                        }
                        case 4: {
                            service.showAllContacts(readerKeyboard);
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
            } catch (ApplicationException | IOException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
        while (exit);
    }

    static boolean isCorrectInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println(ResponseCode.WRONG_DATA_TYPE + " " + MassageApp.DATA_TYPE_IS_NOT_NUMBER);
            return false;
        }
        return true;
    }

    static boolean isCorrectDouble(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println(ResponseCode.WRONG_DATA_TYPE + " " + MassageApp.DATA_TYPE_IS_NOT_NUMBER);
            return false;
        }
        return true;
    }
}

