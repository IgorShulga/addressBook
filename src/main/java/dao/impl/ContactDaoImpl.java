package dao.impl;

import constants.MassageApp;
import dao.ContactDao;
import service.CommandLIneService;
import entity.Contact;
import exception.ApplicationException;
import constants.ResponseCode;

import java.sql.*;
import java.util.*;

public class ContactDaoImpl implements ContactDao, CommandLIneService {

    public void saveContact(Contact contact) {
        try (Connection connection = DriverManager.getConnection(FULL_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACT)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurName());
            preparedStatement.setString(3, contact.getPhoneNumber());
            preparedStatement.setInt(4, contact.getAge());
            preparedStatement.setDouble(5, contact.getHeight());
            preparedStatement.setBoolean(6, contact.isMarried());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(contact.getCreateDate()));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteContactById(int contactId) {
        try (Connection connection = DriverManager.getConnection(FULL_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONTACT)) {
            preparedStatement.setInt(1, contactId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contact getContactById(int contactId) throws ApplicationException {
        Contact contactForUpdata = null;
        try (Connection connection = DriverManager.getConnection(FULL_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("select * from contacts where id = 5");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                contactForUpdata = new Contact();
                contactForUpdata.setName(resultSet.getString(2));
                contactForUpdata.setSurName(resultSet.getString(3));
                contactForUpdata.setPhoneNumber(resultSet.getString(4));
                contactForUpdata.setAge(resultSet.getInt(5));
                contactForUpdata.setHeight(resultSet.getDouble(6));
                contactForUpdata.setMarried(resultSet.getBoolean(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (contactForUpdata == null) {
            throw new ApplicationException(ResponseCode.NOT_FOUND, MassageApp.THERE_IS_NOT_ID);
        } else {
            return contactForUpdata;
        }
    }

    public void showContacts() {
        try (Connection connection = DriverManager.getConnection(FULL_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute(SELECT_ALL);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println(
                        "ID: " + resultSet.getInt(1) + "  " +
                                "NAME: " + resultSet.getString(2) + "  " +
                                "SURNAME: " + resultSet.getString(3) + "  " +
                                "PHONE NUMBER: " + resultSet.getString(4) + "  " +
                                "AGE: " + resultSet.getBoolean(5) + "  " +
                                "HEIGHT: " + resultSet.getDouble(6) + "  " +
                                "MARRIED: " + resultSet.getBoolean(7) + "  " +
                                "CREATE DATE: " + resultSet.getDate(8));
            }
            System.out.println("That's ALL!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContactById(Contact contact) {
        try (Connection connection = DriverManager.getConnection(FULL_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATA_CONTACT)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurName());
            preparedStatement.setString(3, contact.getPhoneNumber());
            preparedStatement.setInt(4, contact.getAge());
            preparedStatement.setDouble(5, contact.getHeight());
            preparedStatement.setBoolean(6, contact.isMarried());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
