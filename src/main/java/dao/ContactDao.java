package dao;

import entity.Contact;

public class ContactDao {

    public static int generator = 0;

    Contact[] store = new Contact[10];

    public void saveContact(Contact contact) {
        for (Contact elementStore : store) {
            if (elementStore == null) {
                contact.setId(++generator);
                elementStore = contact;
                System.out.println("This contact was added in your contact book.");
                System.out.println(contact.toString());
                break;
            }
        }
    }

    public void findById(int id) {
        for (Contact elementStore : store) {
            if (elementStore.getId() == id) {
                System.out.print("This contact found by your ID: ");
                System.out.println(elementStore.toString());
                break;
            }
        }
    }

    public void showAllContacts() {
        for (Contact elementStore : store) {
            System.out.println(elementStore.toString());
        }
    }

    public void deleteById(int id) {
        for (Contact elementStore : store) {
            if (elementStore.getId() == id) {
                elementStore = null;
                System.out.println("Contact with ID = " + id + " deleted.");
                break;
            }
        }
    }

    public void updataContact(Contact contact) {
        for (Contact elementStore : store) {
            if (elementStore.getId() == contact.getId()) {
                elementStore = contact;
                System.out.print("Your contact updated in your address book: ");
                System.out.println(contact.toString());
                break;
            }
        }
    }

    public void deleteContact(Contact contact) {
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
