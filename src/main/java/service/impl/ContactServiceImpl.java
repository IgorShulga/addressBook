package service.impl;

import constants.MassageApp;
import dao.ContactDao;
import dao.impl.ContactDaoImpl;
import entity.Contact;
import exception.ApplicationException;
import constants.ResponseCode;
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

        System.out.println(MassageApp.ENTER_VALUE_FIELD + " Name - ");
        String name = scanner.next();
        contact.setName(name);

        System.out.println(MassageApp.ENTER_VALUE_FIELD  + " Surname - ");
        String surName = scanner.next();
        contact.setSurNume(surName);

        System.out.println(MassageApp.ENTER_VALUE_FIELD  + " Phone number - ");
        String phoneNumber = scanner.next().replaceAll("[^0-9+]", "");
        contact.setPhoneNumber(phoneNumber);

        contactDaoImpl.saveContact(contact);

        System.out.println(" This contact was added in your contact book. Thank you for using this contact book.");
    }


    @Override
    public Contact updateContact(Scanner scanner) throws ApplicationException {
        if (contactDaoImpl.getStorage().isEmpty()) {
            System.out.println(MassageApp.STORAGE_IS_EMPTY);
            throw new ApplicationException(ResponseCode.STORAGE_IS_EMPTY);
        } else {
            contactDaoImpl.showContacts();
            System.out.println("Enter please contacts ID what you want update");
            int contactId = scanner.nextInt();
            Contact contact = contactDaoImpl.updateContactById(contactId);
            System.out.println("You updating this contact: " + contact.toString());
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
        System.out.println(MassageApp.ENTER_VALUE_FIELD);
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
            System.out.println(MassageApp.STORAGE_IS_EMPTY);
            throw new ApplicationException(ResponseCode.NOT_CONTENT);
        } else {
            contactDaoImpl.showContacts();
            System.out.println("Enter please contacts ID what you want delete");
            int contactIdForDelete = scanner.nextInt();
            contactDaoImpl.deleteContactById(contactIdForDelete);
            System.out.println("Your contact was delete.");
        }
    }

    @Override
    public void showAllContacts(Scanner scanner) {
        contactDaoImpl.showContacts();
    }
}
