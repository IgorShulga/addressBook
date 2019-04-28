package dao.impl;

import constants.MassageApp;
import dao.ContactDao;
import entity.Contact;
import exception.ApplicationException;
import constants.ResponseCode;

import java.util.*;

public class ContactDaoImpl implements ContactDao {

    public static int generator = 0;

    private Set<Contact> storage = new TreeSet(new Comparator<Contact>() {
        @Override
        public int compare(Contact o1, Contact o2) {
            return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
        }
    });

    public void saveContact(Contact contact) throws ApplicationException {
        searchSameContact(contact);
        generator++;
        contact.setId(generator);
        storage.add(contact);
        System.out.println(contact.toString() + " This contact was added in your contact book");
    }


    @Override
    public void deleteContactById(int contactId) throws ApplicationException {
        if (isThereObjectInStorage(contactId)) {
            for (Contact contactFromStorage : storage) {
                if (contactFromStorage.getId() == contactId) {
                    System.out.println("You deleting this contact: " + contactFromStorage.toString());
                    storage.remove(contactFromStorage);
                    break;
                }
            }
        } else {
            System.out.println(MassageApp.ID_DOES_NOT_EXIST);
            throw new ApplicationException(ResponseCode.NOT_CONTENT);
        }

    }

    @Override
    public Contact getContactById(int contactId) throws ApplicationException {
        if (isThereObjectInStorage(contactId)) {
            for (Contact contactFromStorage : storage) {
                if (contactFromStorage.getId() == contactId) {
                    return contactFromStorage;
                }
            }
        }
        System.out.println(MassageApp.THERE_IS_NOT_ID);
        throw new ApplicationException(ResponseCode.NOT_CONTENT);
    }


    public void showContacts() {
        for (Contact contactFromStorage : storage) {
            if (Objects.nonNull(contactFromStorage)) {
                System.out.println(contactFromStorage);
            }
        }
    }

    @Override
    public void deleteContactByEntity(Contact contact) {
        for (Contact contactFromStorage : storage) {
            if (Objects.equals(contactFromStorage, contact)) {
                System.out.println("You deleting this contact: " + contactFromStorage.toString());
                storage.remove(contact);
                break;
            }
        }
    }

    @Override
    public Contact updateContactById(int contactId) throws ApplicationException {
        for (Contact contactFromStorage : storage) {
            if (contactFromStorage.getId() == contactId) {
                System.out.println("You updating this contact: " + contactFromStorage.toString());
                return contactFromStorage;
            }
        }
        System.out.println(MassageApp.ID_DOES_NOT_EXIST);
        throw new ApplicationException(ResponseCode.OBJECT_WAS_NOT_CHANGED);
    }

    @Override
    public Contact getContactByName(String name) throws ApplicationException {
        for (Contact contactFromStorage : storage) {
            if (Objects.equals(contactFromStorage.getName().toLowerCase(), name.toLowerCase())) {
                System.out.println("This contact found by name: " + contactFromStorage.toString());
                return contactFromStorage;
            }
        }
        throw new ApplicationException(ResponseCode.NOT_CONTENT);
    }

    private void searchSameContact(Contact contact) throws ApplicationException {
        for (Contact contactFromStorage : storage) {
            if (Objects.nonNull(contactFromStorage)
                    && contact.getName().equals(contactFromStorage.getName())
                    && contact.getPhoneNumber().equals(contactFromStorage.getPhoneNumber())
                    && contact.getSurNume().equals(contactFromStorage.getSurNume())) {
                System.out.println(MassageApp.OBJECT_EXIST);
                throw new ApplicationException(ResponseCode.OBJECT_EXIST);
            }
        }
    }

    public boolean isThereObjectInStorage(int id) {
        for (Contact contactFromStorage : storage) {
            if (Objects.nonNull(contactFromStorage)) {
                if (contactFromStorage.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteById(int id) throws ApplicationException {

    }

    public Set getStorage() {
        return storage;
    }
}
