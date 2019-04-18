package service;

import exception.ApplicationException;

import java.util.Scanner;

public interface ContactService {

    /**
     * This method created new contact in address book.
     * @param scanner takes keyboard input
     */
    void addContact(Scanner scanner) throws ApplicationException;

    /**
     * This method update contact of address book.
     * @param scanner takes keyboard input
     */
    void updateContact(Scanner scanner) throws ApplicationException;

    /**
     * This method delete contact of address book.
     * @param scanner takes keyboard input
     */
    void deleteContact(Scanner scanner) throws ApplicationException;

    /**
     * This method print all contacts.
     * @param scanner takes keyboard input
     */
    void showAllContacts(Scanner scanner);
}
