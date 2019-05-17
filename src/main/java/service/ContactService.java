package service;

import entity.Contact;
import exception.ApplicationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public interface ContactService {

    int NAME = 1;
    int SUR_NAME = 2;
    int PHONE_NUMBER = 3;
    int AGE = 4;
    int HEIGHT = 5;
    int MARRIED = 6;
    int EXIT = 0;

    /**
     * This method created new contact in address book.
     *
     * @param readerKeyboard takes keyboard input
     */
    void addContact(BufferedReader readerKeyboard) throws ApplicationException, IOException;

    /**
     * This method update contact of address book.
     *
     * @param readerKeyboard takes keyboard input
     */
    Contact updateContact(BufferedReader readerKeyboard) throws ApplicationException, IOException;

    /**
     * This method delete contact of address book.
     *
     * @param readerKeyboard takes keyboard input
     */
    void deleteContact(BufferedReader readerKeyboard) throws ApplicationException, IOException;

    /**
     * This method print all contacts.
     *
     * @param readerKeyboard takes keyboard input
     */
    void showAllContacts(BufferedReader readerKeyboard) throws ApplicationException;
}
