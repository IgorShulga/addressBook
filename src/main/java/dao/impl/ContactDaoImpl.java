package dao.impl;

import dao.ContactDao;
import entity.Contact;

public class ContactDaoImpl implements ContactDao {

    public static int generator = 0;

    private Contact[] store = new Contact[10];

    public void saveContact(Contact contact) {
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
    public void deleteContactById(int id) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument].getId() == id) {
                System.out.println("You deleting this contact: " + store[argument]);
                store[argument] = null;
                break;
            }
        }
    }

    public Contact findContactById(int id) {
        Contact foundContactById = null;
        for (Contact elementStore : store) {
            if (elementStore.getId() == id) {
                foundContactById = elementStore;
                break;
            }
        }
        System.out.print("This contact found by your ID: ");
        System.out.println(foundContactById.toString());
        return foundContactById;
    }

    public Contact findContactByName(String name) {
        Contact foundContactByName = null;
        for (Contact elementStore : store) {
            if (elementStore.getName().equals(name)) {
                foundContactByName = elementStore;
                break;
            }
        }
        System.out.println("This contact found by name: " + foundContactByName.toString());
        return foundContactByName;
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
            if (elementStore.getId() == contactId) {
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

    public Contact[] getStore() {
        return store;
    }
}
