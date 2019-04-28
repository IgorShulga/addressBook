package service.impl;

import dao.ContactDao;
import dao.impl.ContactDaoImpl;
import entity.Contact;
import exception.ApplicationException;
import exception.ResponseCode;
import service.ContactService;

import java.util.Scanner;

public class ContactServiceImpl implements ContactService {

    private ContactDaoImpl contactDaoImpl;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDaoImpl = (ContactDaoImpl) contactDao;
    }

    @Override
    public void addContact(Scanner scanner) throws ApplicationException {
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
    public Contact updateContact(Scanner scanner) throws ApplicationException {
        if (contactDaoImpl.getStorage().isEmpty()) {
            throw new ApplicationException(ResponseCode.NOT_FOUND);
        } else {
            contactDaoImpl.showContacts();
            System.out.println("Enter please contacts ID what you want update");
            int contactId = scanner.nextInt();
            Contact contact = contactDaoImpl.updateContactById(contactId);
            return editContact(scanner, contact);
        }
    }

    private Contact editContact(Scanner scanner, Contact contact) throws ApplicationException {
        boolean exit = true;
        do {
            System.out.println("Choose field for update: " + "\n" +
                    "1 - update Name" + "\n" +
                    "2 - update Sur name" + "\n" +
                    "3 - update phone number" + "\n" +
                    "0 - finish update");
                int number = scanner.nextInt();
                switch (number) {
                    case NAME: {
                        return editFieldOfContact(NAME, contact, scanner);
                    }
                    case SUR_NAME: {
                        return editFieldOfContact(SUR_NAME, contact, scanner);
                    }
                    case PHONE_NUMBER: {
                        return editFieldOfContact(PHONE_NUMBER, contact, scanner);
                    }
                    case EXIT: {
                        System.out.println("You exited from update mode.");
                        exit = false;
                        break;
                    }
                    default: {
                        System.out.println("Sorry. You enter wrong number of menu. We don't change contact. ");
                        throw new ApplicationException(ResponseCode.WRONG_DATA_TYPE);
                    }
                }
        } while (exit);
        return contact;
    }

    private Contact editFieldOfContact(int numberOfField, Contact contact, Scanner scanner) {
        System.out.println("Please enter new value field...");
        String str = scanner.next();
        switch (numberOfField) {
            case ContactService.NAME: {
                contact.setName(str);
                break;
            }
            case ContactService.SUR_NAME: {
                contact.setSurNume(str);
                break;
            }
            case ContactService.PHONE_NUMBER: {
                contact.setPhoneNumber(str);
                break;
            }
        }
        System.out.print("Your contact was updated: ");
        return contact;
    }


    @Override
    public void deleteContact(Scanner scanner) throws ApplicationException {
        if (contactDaoImpl.getStorage().isEmpty()) {
            throw new ApplicationException(ResponseCode.NOT_CONTENT.getStr(), ResponseCode.NOT_CONTENT);
        } else {
            contactDaoImpl.showContacts();
            System.out.println("Enter please contacts ID what you want delete");
            int contactIdForDelete = scanner.nextInt();
            contactDaoImpl.deleteContactById(contactIdForDelete);
        }
    }

    @Override
    public void showAllContacts(Scanner scanner) {
        contactDaoImpl.showContacts();
    }
}
