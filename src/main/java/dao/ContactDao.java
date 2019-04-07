package dao;

import entity.Contact;

public interface ContactDao {

    void saveContact(Contact contact);

    void deleteContactById(int id);

    void deleteContactByEntity (Contact contact);

    void showContacts();

    Contact updataContact(int contactId);

    Contact findContactById(int contactId);

    Contact findContactByName(String name);

}
