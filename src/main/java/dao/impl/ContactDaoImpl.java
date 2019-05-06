package dao.impl;

import constants.MassageApp;
import dao.ContactDao;
import entity.Contact;
import exception.ApplicationException;
import constants.ResponseCode;

import java.util.*;

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
        if (isThereExistContact(contactId)) {
            storage.removeIf(contact -> contact.getId() == contactId);
        } else {
            throw new ApplicationException(ResponseCode.NOT_CONTENT, MassageApp.ID_DOES_NOT_EXIST);
        }
    }

    @Override
    public Contact getContactById(int contactId) throws ApplicationException {
        return Optional.of(storage.stream().
                filter(contactFromStorage -> contactFromStorage.getId() == contactId).findFirst()).get().
                orElseThrow(() -> new ApplicationException(ResponseCode.NOT_FOUND, MassageApp.ID_DOES_NOT_EXIST));
    }


    public void showContacts() throws ApplicationException {
        if(storage.isEmpty()){
            throw new ApplicationException(ResponseCode.STORAGE_IS_EMPTY, MassageApp.STORAGE_IS_EMPTY);
        }
        storage.stream().
                sorted(Comparator.comparing(Contact::getId)).
                forEach(System.out::println);
    }

    @Override
    public Contact updateContactById(int contactId) throws ApplicationException {
        return Optional.of(storage.stream().
                filter(contactFromStorage -> contactFromStorage.getId() == contactId).findFirst()).get().
                orElseThrow(() -> new ApplicationException(ResponseCode.NOT_FOUND, MassageApp.ID_DOES_NOT_EXIST));
    }

    public Contact updateContactByContact(Contact contact) {
        return storage.stream().
                filter(contactFromStorage -> contactFromStorage.equals(contact)).findFirst().get();
    }

    private void searchSameContact(Contact contact) throws ApplicationException {
//        if (Optional.of(storage.stream().anyMatch(contact::equals)).get()) { //second variant implement
        if (Optional.of(storage.contains(contact)).get()) {
            throw new ApplicationException(ResponseCode.OBJECT_EXIST, MassageApp.OBJECT_EXIST);
        }
    }

    private boolean isThereExistContact(int id) {
        return storage.stream().anyMatch(contact -> contact.getId() == id);
    }

    public Set getStorage() {
        return storage;
    }
}
