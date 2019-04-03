package dao;

import entity.Contact;

public class ContactDao {

    public static int generator = 0;

    Contact[] store = new Contact[10];

    public void saveContact(Contact contact) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] == null) {
                contact.setId(++generator);
                store[argument] = contact;
                System.out.println("This contact was added in your contact book.");
                System.out.println(contact.toString());
                break;
            }
        }
    }

    public void findById(int id) {
        for (int index = 0; index < store.length; index++) {
            if (index == id) {
                System.out.print("This contact found by your ID: ");
                System.out.println(store[index].toString());
                break;
            }
        }
    }

    public void showAllContacts() {
        for (Contact contact : store) {
            System.out.println(contact.toString());
        }
    }

    public void deleteById(int id) {
        for (int index = 0; index < store.length; index++) {
            if (index == id) {
                store[index] = null;
                System.out.println("Contact with ID = " + index + " deleted.");
                break;
            }
        }
    }

    public void updataContact(Contact contact) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument].getId() == contact.getId()) {
                store[argument] = contact;
                System.out.println("Your contact updated in your address book");
                System.out.println(contact.toString());
                break;
            }
        }
    }

    public void deleteContact(Contact contact) {
        for(int argument = 0; argument < store.length; argument++){
            if(store[argument].getId() == contact.getId()){
                store[argument] = null;
                System.out.println("Your contact deleted");
            }
        }
    }

    public Contact[] getStore() {
        return store;
    }
}
