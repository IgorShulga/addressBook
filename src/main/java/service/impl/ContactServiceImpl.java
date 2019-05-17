package service.impl;

import constants.MassageApp;
import dao.ContactDao;
import dao.impl.ContactDaoImpl;
import entity.Contact;
import exception.ApplicationException;
import constants.ResponseCode;
import service.CommandLIneService;
import service.ContactService;

import java.io.BufferedReader;
import java.io.IOException;

public class ContactServiceImpl extends CommandLineServiceImpl implements ContactService {

    private ContactDaoImpl contactDaoImpl;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDaoImpl = (ContactDaoImpl) contactDao;
    }

    @Override
    public void addContact(BufferedReader readerKeyboard) throws ApplicationException, IOException {
        Contact contact = new Contact();

        System.out.println(MassageApp.ENTER_VALUE_FIELD + " Name - ");
        String name = readerKeyboard.readLine();
        contact.setName(name);

        System.out.println(MassageApp.ENTER_VALUE_FIELD + " Surname - ");
        String surName = readerKeyboard.readLine();
        contact.setSurNume(surName);

        System.out.println(MassageApp.ENTER_VALUE_FIELD + " Phone number - ");
        String phoneNumber = readerKeyboard.readLine().replaceAll("[^0-9+]", "");
        contact.setPhoneNumber(phoneNumber);

        System.out.println(MassageApp.ENTER_VALUE_FIELD + " Age - ");
        String stringAge = readerKeyboard.readLine();
        if (CommandLIneService.isCorrectInteger(stringAge)) {
            int age = Integer.parseInt(stringAge);
            contact.setAge(age);
        }

        System.out.println(MassageApp.ENTER_VALUE_FIELD + " Height (m.cm) - ");
        String stringHeight = readerKeyboard.readLine();
        if (CommandLIneService.isCorrectDouble(stringHeight)) {
            double height = Double.parseDouble(stringHeight);
            contact.setHeight(height);
        }

        System.out.println(MassageApp.ENTER_VALUE_FIELD + " Are you married? " +
                "(Enter '1' if you unmarried and '2' if you married) - ");
        boolean married;
        String entreString = null;
        entreString = readerKeyboard.readLine();
        if (entreString.equals("2")) {
            married = true;
        } else {
            married = false;
        }
        contact.setMarried(married);

        contactDaoImpl.saveContact(contact);

        System.out.println(" This contact was added in your contact book. Thank you for using this contact book.");
    }

    @Override
    public Contact updateContact(BufferedReader readerKeyboard) throws ApplicationException, IOException {
        if (!contactDaoImpl.getStorage().isEmpty()) {
            contactDaoImpl.showContacts();
            System.out.println("Enter please contacts ID what you want update");
            String string = readerKeyboard.readLine();
            if (CommandLIneService.isCorrectInteger(string)) {
                int index = Integer.parseInt(string);
                Contact contact = contactDaoImpl.getContactById(index);
                contactDaoImpl.updateContactById(index);
                System.out.println("You updating this contact: " + contact.toString());
                return editContact(readerKeyboard, contact);
            }
        }
        throw new ApplicationException(ResponseCode.STORAGE_IS_EMPTY, MassageApp.STORAGE_IS_EMPTY);
    }

    private Contact editContact(BufferedReader readerKeyboard, Contact contact) throws ApplicationException, IOException {
        boolean exit = true;
        do {
            System.out.println("Choose field for update: " + "\n" +
                    "1 - update Name" + "\n" +
                    "2 - update Sur name" + "\n" +
                    "3 - update phone number" + "\n" +
                    "4 - update age" + "\n" +
                    "5 - update height" + "\n" +
                    "6 - update married" + "\n" +
                    "0 - finish update");
            String tempString = readerKeyboard.readLine();
            if (CommandLIneService.isCorrectInteger(tempString)) {
                int number = Integer.parseInt(tempString);
                switch (number) {
                    case NAME: {
                        return editFieldOfContact(NAME, contact, readerKeyboard);
                    }
                    case SUR_NAME: {
                        return editFieldOfContact(SUR_NAME, contact, readerKeyboard);
                    }
                    case PHONE_NUMBER: {
                        return editFieldOfContact(PHONE_NUMBER, contact, readerKeyboard);
                    }
                    case AGE: {
                        return editFieldOfContact(AGE, contact, readerKeyboard);
                    }
                    case HEIGHT: {
                        return editFieldOfContact(HEIGHT, contact, readerKeyboard);
                    }
                    case MARRIED: {
                        return editFieldOfContact(MARRIED, contact, readerKeyboard);
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
            }
        } while (exit);
        return contact;
    }

    private Contact editFieldOfContact(int numberOfField, Contact contact, BufferedReader readerKeyboard) throws IOException {
        System.out.println(MassageApp.ENTER_VALUE_FIELD);
        String tempString = readerKeyboard.readLine();
        CommandLIneService.isCorrectInteger(tempString);
        switch (numberOfField) {
            case ContactService.NAME: {
                contact.setName(tempString);
                break;
            }
            case ContactService.SUR_NAME: {
                contact.setSurNume(tempString);
                break;
            }
            case ContactService.PHONE_NUMBER: {
                contact.setPhoneNumber(tempString);
                break;
            }
            case ContactService.AGE: {
                contact.setAge(Integer.parseInt(tempString));
                break;
            }
            case ContactService.HEIGHT: {
                contact.setHeight(Integer.parseInt(tempString));
                break;
            }
            case ContactService.MARRIED: {
                contact.setMarried(Boolean.parseBoolean(tempString));
                break;
            }
        }
        System.out.print("Your contact was updated: ");
        return contact;
    }

    @Override
    public void deleteContact(BufferedReader readerKeyboard) throws ApplicationException, IOException {
        if (contactDaoImpl.getStorage().isEmpty()) {
            throw new ApplicationException(ResponseCode.NOT_CONTENT, MassageApp.STORAGE_IS_EMPTY);
        } else {
            contactDaoImpl.showContacts();
            System.out.println("Enter please contacts ID what you want delete");
            String stringTemp = readerKeyboard.readLine();
            if (CommandLIneService.isCorrectInteger(stringTemp)) {
                int contactIdForDelete = Integer.parseInt(stringTemp);
                contactDaoImpl.deleteContactById(contactIdForDelete);
                System.out.println("Your contact was delete.");
            }
        }
    }

    @Override
    public void showAllContacts(BufferedReader readerKeyboard) throws ApplicationException {
        contactDaoImpl.showContacts();
    }
}
