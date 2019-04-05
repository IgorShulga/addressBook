package service;

import entity.Contact;

import java.util.Scanner;

public interface ContactService {

    void addContact(Scanner scanner);

    void updateContact(Scanner scanner);

    void deleteContact(Scanner scanner);

    void showAllContacts(Scanner scanner);

    void exit(Scanner scanner);

}
