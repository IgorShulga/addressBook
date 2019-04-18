package dao.impl;

import dao.ContactDao;
import entity.Contact;
import exception.ApplicationException;
import exception.ResponseCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactDaoImpl implements ContactDao {

    public static int generator = 0;

    private List<Contact> store = new ArrayList<>();

    public void saveContact(Contact contact) throws ApplicationException {

        searchSameContact(contact);
        for (int argument = 0; argument < store.length; argument++) {
            if (Objects.isNull(store[argument])) {
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
    public void deleteContactById(int contactId) throws ApplicationException {
        if (isThereId(contactId)) {
            throw new ApplicationException("There isn't this ID", ResponseCode.NOT_CONTENT);
        }
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument].getId() == contactId) {
                System.out.println("You deleting this contact: " + store[argument]);
                store[argument] = null;
                break;
            }
        }
    }

    @Override
    public Contact getContactById(int contactId) throws ApplicationException {
        if (isThereId(contactId)) {
            throw new ApplicationException("There isn't this ID", ResponseCode.NOT_CONTENT);
        }
        for (Contact storeContacts : getStore()) {
            if (storeContacts.getId() == contactId) {
                return storeContacts;
            }
        }
        return null;
    }

    public void showContacts() {
        for (Contact contactStore : store) {
            if (Objects.nonNull(contactStore)) {
                System.out.println(contactStore);
            }
        }
    }

    private void searchSameContact(Contact contact) throws ApplicationException {
        for (Contact contactFromStore : getStore()) {
            if (Objects.nonNull(contactFromStore)
                    && contact.getName().equals(contactFromStore.getName())
                    && contact.getPhoneNumber().equals(contactFromStore.getPhoneNumber())
                    && contact.getSurNume().equals(contactFromStore.getSurNume())) {
                throw new ApplicationException(ResponseCode.OBJECT_EXIST.getStr(), ResponseCode.OBJECT_EXIST);
            }
        }
    }

    public boolean isThereId(int id) {
        for (Contact contact : getStore()) {
            if (Objects.nonNull(contact)) {
                if (contact.getId() == id) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEmptyStore() {
        for (Contact contact : getStore()) {
            if (Objects.nonNull(contact)) {
                return false;
            }
        }
        return true;
    }

    public Contact[] getStore() {
        return store;
    }

    @Override
    public void deleteContactByEntity(Contact contact) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument].equals(contact)) {
                System.out.println("You deleting this contact: " + store[argument].toString());
                store[argument] = null;
                break;
            }
        }
    }

    @Override
    public Contact updateContactById(int contactId) {
        Contact contactTemp = null;
        for (Contact elementStore : store) {
            if (elementStore.getId() == contactId) {
                System.out.println("You updating this contact: " + elementStore.toString());
                contactTemp = elementStore;
                break;
            }
        }
        return contactTemp;
    }

    @Override
    public Contact getContactByName(String name) {
        for (Contact elementStore : store) {
            if (Objects.equals(elementStore.getName().toLowerCase(), name.toLowerCase())) {
                System.out.println("This contact found by name: " + elementStore.toString());
                return elementStore;
            }
        }
        return null;
    }
}
