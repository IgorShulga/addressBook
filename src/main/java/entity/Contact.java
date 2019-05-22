package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Contact {
    private int id;
    private String name;
    private String surNume;
    private String phoneNumber;
    private int age;
    private double height;
    private boolean married;
    private LocalDateTime createDate;

    public Contact() {
    }

    public Contact(String name, String surNume, String phoneNumber,
                   int age, double height, boolean married, LocalDateTime createDate) {
        this.name = name;
        this.surNume = surNume;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.height = height;
        this.married = married;
        this.createDate = createDate;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
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
                "id = " + id +
                ", name = '" + name + '\'' +
                ", surNume = '" + surNume + '\'' +
                ", phoneNumber = '" + phoneNumber + '\'' +
                ", age = " + age +
                ", height = " + height +
                ", married = " + married +
                ", createDate = " + createDate.format(DateTimeFormatter.ofPattern("d MMMM uuuu; HH:mm:ss")) +
                '}';
    }
}
