package dao.impl;

import dao.ContactDao;
import entity.Contact;
import exception.ApplicationException;
import exception.ResponseCode;

import java.util.Objects;

public class ContactDaoImpl implements ContactDao {

    public static int generator = 0;

    private Contact[] store = new Contact[10];

    public void saveContact(Contact contact) throws ApplicationException {
        for (Contact contact1 : getStore()) {
            if (Objects.nonNull(contact1) &&
                    contact.getName().equals(contact1.getName()) &&
                    contact.getSurNume().equals(contact1.getSurNume()) &&
                    contact.getPhoneNumber().equals(contact1.getPhoneNumber())) {
                throw new ApplicationException(ResponseCode.OBJECT_EXIST.getStr(), ResponseCode.OBJECT_EXIST);
            }
        }

        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] == null) {
                generator = argument;
                contact.setId(++generator);
                store[argument] = contact;
                System.out.println("This contact was added in your contact book.");
                System.out.println(contact.toString());
                break;
            }
        }
    }

    @Override
    public void deleteContactById(int id) throws ApplicationException {
        if (!isThereId(id)) {
            throw new ApplicationException("There isn't this ID", ResponseCode.NOT_CONTENT);
        }
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument].getId() == id) {
                System.out.println("You deleting this contact: " + store[argument]);
                store[argument] = null;
                break;
            }
        }
    }

    public boolean isThereId(int id) {
        boolean isThere = false;
        for (Contact contact : getStore()) {
            if (contact != null) {
                if (contact.getId() == id) {
                    isThere = true;
                    break;
                }
            }
        }
        return isThere;
    }

    public Contact getContactById(int contactId) throws ApplicationException {
        if (!isThereId(contactId)) {
            throw new ApplicationException("There isn't this ID", ResponseCode.NOT_CONTENT);
        }
        for (Contact storeContacts : getStore()) {
            if (storeContacts.getId() == contactId) {
                return storeContacts;
            }
        }
        return null;
    }

    public Contact getContactByName(String name) {
        for (Contact elementStore : store) {
            if (Objects.equals(elementStore.getName().toLowerCase(), name.toLowerCase())) {
                System.out.println("This contact found by name: " + elementStore.toString());
                return elementStore;
            }
        }
        return null;
    }

    public void showContacts() {
        for (Contact contactStore : store) {
            if (contactStore != null) {
                System.out.println(contactStore);
            }
        }
    }

    @Override
    public Contact updateContactById(int contactId) {
        Contact contactTemp = null;
        for (Contact elementStore : store) {
            if (Objects.equals(elementStore.getId(), contactId)) {
                System.out.println("You updating this contact: " + elementStore.toString());
                contactTemp = elementStore;
                break;
            }
        }
        return contactTemp;
    }

    public void deleteContactByEntity(Contact contact) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument].equals(contact)) {
                System.out.println("You deleting this contact: " + store[argument].toString());
                store[argument] = null;
                break;
            }
        }
    }

    public boolean isEmptyStore() {
        boolean empty = true;
        for (Contact contact : getStore()) {
            if (contact != null) {
                empty = false;
                break;
            }
        }
        return empty;
    }

    public Contact[] getStore() {
        return store;
    }
}
