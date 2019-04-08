package service;

import java.util.Scanner;

public interface ContactService {

    /**
     * This method created new contact in address book.
     *
     * @param scanner takes keyboard input
     */
    void addContact(Scanner scanner);

    /**
     * This method update contact of address book.
     *
     * @param scanner takes keyboard input
     */
    void updateContact(Scanner scanner);

    /**
     * This method delete contact of address book.
     *
     * @param scanner takes keyboard input
     */
    void deleteContact(Scanner scanner);

    /**
     * This method print all contacts.
     *
     * @param scanner takes keyboard input
     */
    void showAllContacts(Scanner scanner);

    /**
     * This method finish of app and exit from it.
     *
     * @param scanner takes keyboard input
     */
    void exit(Scanner scanner);

}
