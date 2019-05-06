package service;

import entity.Contact;
import exception.ApplicationException;

import java.util.Scanner;

public interface ContactService {

    int NAME = 1;
    int SUR_NAME = 2;
    int PHONE_NUMBER = 3;
    int EXIT = 0;

    /**
     * This method created new contact in address book.
     *
     * @param scanner takes keyboard input
     */
    void addContact(Scanner scanner) throws ApplicationException;

    /**
     * This method update contact of address book.
     *
     * @param scanner takes keyboard input
     */
    Contact updateContact(Scanner scanner) throws ApplicationException;

    /**
     * This method delete contact of address book.
     *
     * @param scanner takes keyboard input
     */
    void deleteContact(Scanner scanner) throws ApplicationException;

    /**
     * This method print all contacts.
     *
     * @param scanner takes keyboard input
     */
    void showAllContacts(Scanner scanner) throws ApplicationException;
}
