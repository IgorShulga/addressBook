package service.impl;

import dao.ContactDao;
import dao.impl.ContactDaoImpl;
import entity.Contact;
import service.ContactService;

import java.util.Scanner;

public class ContactServiceImpl implements ContactService {

    private ContactDaoImpl contactDaoImpl;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDaoImpl = contactDaoImpl;
    }

    @Override
    public void addContact(Scanner scanner) {
        Contact contact = new Contact();

        System.out.println("Enter please name of your contact person:");
        String name = scanner.next();
        contact.setName(name);

        System.out.println("Enter please sur name of your contact person:");
        String surName = scanner.next();
        contact.setSurNume(surName);

        System.out.println("Enter please phone number of your contact contact:");
        String phoneNumber = scanner.next().replaceAll("[^0-9+]", "");
        contact.setPhoneNumber(phoneNumber);

        contactDaoImpl.saveContact(contact);

        System.out.println("Thank you for saving your contact in this contact book.");
    }

    @Override
    public void updateContact(Scanner scanner) {

    }

    @Override
    public void deleteContact(Scanner scanner) {

    }

    @Override
    public void showAllContacts(Scanner scanner) {

    }

    @Override
    public void exit(Scanner scanner) {

    }
}
