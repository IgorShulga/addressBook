package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Contact {
    private int id;
    private String name;
    private String surName;
    private String phoneNumber;
    private int age;
    private double height;
    private boolean married;
    private LocalDateTime createDate;

    public Contact() {
    }

    public Contact(String name, String surName, String phoneNumber,
                   int age, double height, boolean married, LocalDateTime createDate) {
        this.name = name;
        this.surName = surName;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
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
        if (!Objects.equals(surName, contact.surName)) return false;
        return Objects.equals(phoneNumber, contact.phoneNumber);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", surName = '" + surName + '\'' +
                ", phoneNumber = '" + phoneNumber + '\'' +
                ", age = " + age +
                ", height = " + height +
                ", married = " + married +
                ", createDate = " + createDate.format(DateTimeFormatter.ofPattern("d MMMM uuuu; HH:mm:ss")) +
                '}';
    }
}
