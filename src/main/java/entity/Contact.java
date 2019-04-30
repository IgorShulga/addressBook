package entity;

import java.util.Objects;

public class Contact {
    private int id;
    private String name;
    private String surNume;
    private String phoneNumber;

    public Contact() {
    }

    public Contact(String name, String surNume, String phoneNumber) {
        this.name = name;
        this.surNume = surNume;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurNume() {
        return surNume;
    }

    public void setSurNume(String surNume) {
        this.surNume = surNume;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!Objects.equals(name, contact.name)) return false;
        if (!Objects.equals(surNume, contact.surNume)) return false;
        return Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surNume != null ? surNume.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surNume='" + surNume + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
