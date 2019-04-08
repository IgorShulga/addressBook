package service.impl;

import dao.ContactDao;
import dao.impl.ContactDaoImpl;
import entity.Contact;
import service.ContactService;

import java.util.Scanner;

public class ContactServiceImpl implements ContactService {

    private ContactDaoImpl contactDaoImpl;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDaoImpl = (ContactDaoImpl) contactDao;
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
        contactDaoImpl.showContacts();
        System.out.println("Enter please contacts ID what you want update");
        int contactId = scanner.nextInt();
        Contact newContact = contactDaoImpl.updateContactById(contactId);
        boolean exit = true;
        do {
            System.out.println("Choose your field for update:");
            System.out.println("1 - update Name");
            System.out.println("2 - update Sur name");
            System.out.println("3 - update phone number");
            System.out.println("0 - finish update");
            int numberOfMenu = scanner.nextInt();
            switch (numberOfMenu) {
                case 1: {
                    System.out.println("Please update name of your contact person:");
                    newContact.setName(scanner.next());
                    break;
                }
                case 2: {
                    System.out.println("Please update sur name of your contact person:");
                    newContact.setSurNume(scanner.next());
                    break;
                }
                case 3: {
                    System.out.println("Please, update phone number of your contact person:");
                    newContact.setPhoneNumber(scanner.next());
                    break;
                }
                case 0: {
                    System.out.print("Your contact udated: ");
                    System.out.println(newContact.toString());
                    exit = false;
                    break;
                }
                default: {
                    System.out.println("Sorry. You enter wrong number of menu. Choose another number.");
                }
            }
        } while (exit);
    }

    @Override
    public void deleteContact(Scanner scanner) {
        contactDaoImpl.showContacts();
        System.out.println("Enter please contacts ID what you want delete");
        int contactIdForDelete = scanner.nextInt();
        contactDaoImpl.deleteContactById(contactIdForDelete);
    }

    @Override
    public void showAllContacts(Scanner scanner) {
        contactDaoImpl.showContacts();
    }
}
