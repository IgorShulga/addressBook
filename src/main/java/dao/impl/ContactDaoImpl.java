package dao.impl;

import constants.MassageApp;
import dao.ContactDao;
import entity.Contact;
import exception.ApplicationException;
import constants.ResponseCode;

import java.util.*;
import java.util.stream.Stream;

public class ContactDaoImpl implements ContactDao {

    public static int generator = 0;

    private Set<Contact> storage = new TreeSet(Comparator.comparing(Object::hashCode));

    public void saveContact(Contact contact) throws ApplicationException {
        searchSameContact(contact);
        generator++;
        contact.setId(generator);
        storage.add(contact);
    }

    @Override
    public void deleteContactById(int contactId) throws ApplicationException {
        if (isThereObjectInStorage(contactId)) {
            for (Contact contactFromStorage : storage) {
                if (contactFromStorage.getId() == contactId) {
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
    public Contact getContactById(int contactId) {
        return storage.stream().
                filter(contactFromStorage -> contactFromStorage.getId() == contactId).findFirst().get();
    }

    public void showContacts() {
        Stream<Contact> sortContacts = storage.stream().sorted(Comparator.comparing(Contact::getId));
        sortContacts.forEach(System.out::println);
    }

    @Override
    public Contact updateContactById(int contactId) {
        return storage.stream().
                filter(contactFromStorage -> contactFromStorage.getId() == contactId).findFirst().get();
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

    private boolean isThereObjectInStorage(int id) {
        return storage.stream().
                anyMatch(contact -> contact.getId() == id);
    }

    public Set getStorage() {
        return storage;
    }
}
