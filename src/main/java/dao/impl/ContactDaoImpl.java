package dao.impl;

import dao.ContactDao;
import entity.Contact;
import exception.ApplicationException;
import exception.ResponseCode;

import java.util.*;

public class ContactDaoImpl implements ContactDao {

    public static int generator = 0;

    private Set<Contact> storage = new TreeSet(new Comparator<Contact>(){
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
        if (isThereId(contactId)) {
            throw new ApplicationException("There isn't this ID", ResponseCode.NOT_CONTENT);
        }
        for (Contact contactFromStorage : storage) {
            if (contactFromStorage.getId() == contactId) {
                System.out.println("You deleting this contact: " + contactFromStorage.toString());
                storage.remove(contactFromStorage);
                break;
            }
        }
    }

    @Override
    public Contact getContactById(int contactId) throws ApplicationException {
        if (isThereId(contactId)) {
            throw new ApplicationException("There isn't this ID", ResponseCode.NOT_CONTENT);
        }
        for (Contact contactFromStorage : storage) {
            if (contactFromStorage.getId() == contactId) {
                return contactFromStorage;
            }
        }
        return null;
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
    public Contact updateContactById(int contactId) {
        for (Contact contactFromStorage : storage) {
            if (contactFromStorage.getId() == contactId) {
                System.out.println("You updating this contact: " + contactFromStorage.toString());
                return contactFromStorage;
            }
        }
        return null;
    }

    @Override
    public Contact getContactByName(String name) {
        for (Contact contactFromStorage : storage) {
            if (Objects.equals(contactFromStorage.getName().toLowerCase(), name.toLowerCase())) {
                System.out.println("This contact found by name: " + contactFromStorage.toString());
                return contactFromStorage;
            }
        }
        return null;
    }

    private void searchSameContact(Contact contact) throws ApplicationException {
        for (Contact contactFromStorage : storage) {
            if (Objects.nonNull(contactFromStorage)
                    && contact.getName().equals(contactFromStorage.getName())
                    && contact.getPhoneNumber().equals(contactFromStorage.getPhoneNumber())
                    && contact.getSurNume().equals(contactFromStorage.getSurNume())) {
                throw new ApplicationException(ResponseCode.OBJECT_EXIST.getStr(), ResponseCode.OBJECT_EXIST);
            }
        }
    }

    public boolean isThereId(int id) {
        for (Contact contactFromStorage : storage) {
            if (Objects.nonNull(contactFromStorage)) {
                if (contactFromStorage.getId() == id) {
                    return false;
                }
            }
        }
        return true;
    }

    public Set getStorage() {
        return storage;
    }
}
