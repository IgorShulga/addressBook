package dao;

import entity.Contact;
import exception.ApplicationException;

public interface ContactDao {

    /**
     * This method save new contact in address book.
     *
     * @param contact new contact of address book
     */
    void saveContact(Contact contact) throws ApplicationException;

    /**
     * This method delete contact of address book by id of contact.
     *
     * @param id id of contact
     */
    void deleteContactById(int id) throws ApplicationException;

    /**
     * This method delete contact of address book.
     *
     * @param contact contact of address book
     */
    void deleteContactByEntity(Contact contact);

    /**
     * This method print all contacts of address book.
     */
    void showContacts();

    /**
     * This method update all fields the contact.
     *
     * @param contactId id of contact
     * @return contact of address book
     */
    Contact updateContactById(int contactId) throws ApplicationException;

    /**
     * This method looking for contact by id.
     *
     * @param contactId id of contact
     * @return contact of address book
     */
    Contact getContactById(int contactId) throws ApplicationException;

    /**
     * This method looking for contact by name.
     *
     * @param name name of contact
     * @return contact of address book
     */
    Contact getContactByName(String name) throws ApplicationException;

}
