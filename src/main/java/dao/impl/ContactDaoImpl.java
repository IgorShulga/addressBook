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
        System.out.print("This contact found by name: ");
        System.out.println(foundContactByName.toString());
        return foundContactByName;
    }


    public void showContacts() {
        for (Contact contactStore : store) {
            if(contactStore != null) {
                System.out.println(contactStore);
            }
        }
    }

    public void deleteContactById(int id) {
        for (Contact elementStore : store) {
            if (elementStore.getId() == id) {
                elementStore = null;
                System.out.println("Contact with ID = " + id + " deleted.");
                break;
            }
        }
    }

    public Contact updataContact(int contactId) {
        Contact updatedContact = null;
        for (Contact elementStore : store) {
            if (elementStore.getId() == contactId) {
                elementStore = updatedContact;
                break;
            }
        }
        System.out.print("Your contact updated in your address book: ");
        System.out.println(updatedContact);
        return updatedContact;
    }

    public void deleteContactByEntity(Contact contact) {
        for (Contact elementStore : store) {
            if (elementStore.equals(contact)) {
                elementStore = null;
                System.out.println("Your contact deleted");
                break;
            }
        }
    }


    public Contact[] getStore() {
        return store;
    }
}
