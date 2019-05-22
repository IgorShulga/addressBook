package service.impl;

import constants.MassageApp;
import dao.ContactDao;
import dao.impl.ContactDaoImpl;
import entity.Contact;
import exception.ApplicationException;
import constants.ResponseCode;
import service.CommandLIneService;
import service.ContactService;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;


public class ContactServiceImpl extends CommandLineServiceImpl implements ContactService {

    private static final String ID = "ID: ";
    private static final String NAME = "Name: ";
    private static final String SUR_NAME = "Surname: ";
    private static final String PHONE_NUMBER = "Phone Number: ";
    private static final String AGE = "Age: ";
    private static final String HEIGHT = "Height: ";
    private static final String MARRIED = "Married: ";
    private static final String CREATE_DATE = "Create date: ";
    private static final String WORD_SEPARATOR = "; ";
    private static final String SET_PATH = "./backup/";
    private static final String FILE_NAME = "contacts_" +
            LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + ".txt";

    private ContactDaoImpl contactDaoImpl;

    ContactServiceImpl(ContactDao contactDao) {
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

        contact.setCreateDate(LocalDateTime.now());

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

    private Contact editContact(BufferedReader readerKeyboard, Contact contact) throws IOException, ApplicationException {
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
                    case NAME_BUTTON: {
                        return editFieldOfContact(NAME_BUTTON, contact, readerKeyboard);
                    }
                    case SUR_NAME_BUTTON: {
                        return editFieldOfContact(SUR_NAME_BUTTON, contact, readerKeyboard);
                    }
                    case PHONE_NUMBER_BUTTON: {
                        return editFieldOfContact(PHONE_NUMBER_BUTTON, contact, readerKeyboard);
                    }
                    case AGE_BUTTON: {
                        return editFieldOfContact(AGE_BUTTON, contact, readerKeyboard);
                    }
                    case HEIGHT_BUTTON: {
                        return editFieldOfContact(HEIGHT_BUTTON, contact, readerKeyboard);
                    }
                    case MARRIED_BUTTON: {
                        return editFieldOfContact(MARRIED_BUTTON, contact, readerKeyboard);
                    }
                    case EXIT_BUTTON: {
                        System.out.println("You exited from update mode.");
                        exit = false;
                        break;
                    }
                    default: {
                        System.out.println(MassageApp.WRONG_DATA_TYPE);
                    }
                }
            }
        } while (exit);
        return contact;
    }

    private Contact editFieldOfContact(int numberOfField, Contact contact, BufferedReader readerKeyboard) throws IOException, ApplicationException {
        System.out.println(MassageApp.ENTER_VALUE_FIELD);
        String tempString = readerKeyboard.readLine();
        switch (numberOfField) {
            case ContactService.NAME_BUTTON: {
                contact.setName(tempString);
                break;
            }
            case ContactService.SUR_NAME_BUTTON: {
                contact.setSurNume(tempString);
                break;
            }
            case ContactService.PHONE_NUMBER_BUTTON: {
                contact.setPhoneNumber(tempString);
                break;
            }
            case ContactService.AGE_BUTTON: {
                if (CommandLIneService.isCorrectInteger(tempString)) {
                    contact.setAge(Integer.parseInt(tempString));
                }
                break;
            }
            case ContactService.HEIGHT_BUTTON: {
                if (CommandLIneService.isCorrectDouble(tempString)) {
                    contact.setHeight(Double.parseDouble(tempString));
                }
                break;
            }
            case ContactService.MARRIED_BUTTON: {
                contact.setMarried(Boolean.parseBoolean(tempString));
                break;
            }
            default: {
                System.out.println("You entered 123");
            }
        }
        System.out.print("Your contact was updated. ");
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

    void checkCreateAndReadFile() {
        File folder = new File(SET_PATH);
        File[] files = folder.listFiles();
        Optional<File> lastModifiedFile = Arrays
                .stream(files)
                .max(Comparator.comparingLong(f -> f.toPath()
                        .toFile()
                        .lastModified()));
        if (lastModifiedFile.isPresent()) {
            File backupFile = new File(lastModifiedFile.get().getAbsolutePath());
            try {
                if (backupFile.exists()) {
                    System.out.println("File exist. We read our file and write into storage.");
                    BufferedReader readerFile = new BufferedReader(new FileReader(backupFile));
                    readerFile
                            .lines()
                            .forEach((String note) -> {
                                        Contact contact = new Contact();
                                        String[] arrayValue = note.split(WORD_SEPARATOR);
                                        for (String value : arrayValue) {
                                            if (value.contains(NAME)) {
                                                contact.setName(value.split(":")[1].trim());
                                            }
                                            if (value.contains(SUR_NAME)) {
                                                contact.setSurNume(value.substring(value.indexOf(":") + 1).trim());
                                            }
                                            if (value.contains(PHONE_NUMBER)) {
                                                contact.setPhoneNumber(value.substring(value.indexOf(":") + 1).trim());
                                            }
                                            if (value.contains(AGE)) {
                                                contact.setAge(Integer.parseInt(value.substring(value.indexOf(":") + 1).trim()));
                                            }
                                            if (value.contains(HEIGHT)) {
                                                contact.setHeight(Double.parseDouble(value.substring(value.indexOf(":") + 1).trim()));
                                            }
                                            if (value.contains(MARRIED)) {
                                                contact.setMarried(Boolean.parseBoolean(value.substring(value.indexOf(":") + 1).trim()));
                                            }
                                            if (value.contains(CREATE_DATE)) {
                                                contact.setCreateDate(LocalDateTime.parse(value.substring(value.indexOf(":") + 1).trim(), DateTimeFormatter.ISO_DATE_TIME));
                                            }
                                        }
                                        try {
                                            contactDaoImpl.saveContact(contact);
                                        } catch (ApplicationException e) {
                                            e.printStackTrace();
                                        }
                                    }
                            );
                    readerFile.close();
                } else {
                    System.out.println("File not existed.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void writeFromSetToFile(Set<Contact> contacts) throws IOException {
        BufferedWriter writerToFile = new BufferedWriter(new FileWriter(SET_PATH + FILE_NAME));
        for (Contact contact : contacts) {
            writerToFile.write(NAME + contact.getName() + WORD_SEPARATOR +
                    SUR_NAME + contact.getSurNume() + WORD_SEPARATOR +
                    PHONE_NUMBER + contact.getPhoneNumber() + WORD_SEPARATOR +
                    AGE + contact.getAge() + WORD_SEPARATOR +
                    HEIGHT + contact.getHeight() + WORD_SEPARATOR +
                    MARRIED + contact.isMarried() + WORD_SEPARATOR +
                    CREATE_DATE + contact.getCreateDate());
            writerToFile.newLine();
        }
        writerToFile.close();
    }

    Set<Contact> getStoreForWrite() {
        return contactDaoImpl.getStorage();
    }

    void checkAndCreateDir() {
        if (new File(SET_PATH).mkdirs()) {
            System.out.println("create dir");
        } else {
            System.out.println("dir exist");
        }
    }
}