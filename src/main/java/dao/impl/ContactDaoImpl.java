package dao.impl;

import constants.MassageApp;
import dao.ContactDao;
import entity.Contact;
import exception.ApplicationException;
import constants.ResponseCode;

import java.util.*;
import java.util.stream.Stream;

public class ContactDaoImpl implements ContactDao {

    private Set<Contact> storage = new TreeSet<>(Comparator.comparing(Contact::getName).
            thenComparing(Contact::getSurNume).
            thenComparing(Contact::getPhoneNumber));

    public static int generator = 0;

    public void saveContact(Contact contact) throws ApplicationException {
        searchSameContact(contact);
        generator++;
        contact.setId(generator);
        storage.add(contact);
    }

    @Override
    public void deleteContactById(int contactId) throws ApplicationException {
        if (isThereObjectInStorage(contactId)) {
            storage.removeIf(contact -> contact.getId() == contactId);
        } else {
            System.out.println(MassageApp.ID_DOES_NOT_EXIST);
            throw new ApplicationException(ResponseCode.NOT_CONTENT);
        }
    }

    @Override
    public Contact getContactById(int contactId) throws ApplicationException {
        if (!isThereObjectInStorage(contactId)) {
            System.out.println(MassageApp.ID_DOES_NOT_EXIST);
            throw new ApplicationException(ResponseCode.NOT_FOUND);
        } else {
            return storage.stream().
                    filter(contactFromStorage -> contactFromStorage.getId() == contactId).findFirst().get();
        }
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

    public Contact updateContactById(Contact contact) {
        return storage.stream().
                filter(contactFromStorage -> contactFromStorage.equals(contact)).findFirst().get();
    }

    private void searchSameContact(Contact contact) throws ApplicationException {
        if (storage.stream().filter(Objects::nonNull).anyMatch(contact::equals)) {
            System.out.println(MassageApp.OBJECT_EXIST);
            throw new ApplicationException(ResponseCode.OBJECT_EXIST);
        }
    }

    private boolean isThereObjectInStorage(int id) {
        return storage.stream().anyMatch(contact -> contact.getId() == id);
    }

    public Set getStorage() {
        return storage;
    }
}
