package dao.impl;

import constants.MassageApp;
import dao.ContactDao;
import service.CommandLIneService;
import entity.Contact;
import exception.ApplicationException;
import constants.ResponseCode;

import java.sql.*;

public class ContactDaoImpl extends ConnectionDB implements ContactDao, CommandLIneService {

    private static final Connection connection = ConnectionDB.getConnect();

    public void saveContact(Contact contact) throws ApplicationException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACT)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurName());
            preparedStatement.setString(3, contact.getPhoneNumber());
            preparedStatement.setDouble(5, contact.getHeight());
            preparedStatement.setBoolean(6, contact.isMarried());
            preparedStatement.setInt(4, contact.getAge());
            preparedStatement.setString(7, contact.getCreateDate());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ApplicationException(ResponseCode.FAILED_SAVE_DB, MassageApp.FIELD_SAVE_DB);
        }
    }

    @Override
    public void deleteContactById(int contactId) throws ApplicationException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONTACT)) {
            preparedStatement.setInt(1, contactId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationException(ResponseCode.FAILED_DELETE_DB, MassageApp.FIELD_DELETE_DB);
        }
    }

    @Override
    public Contact getContactById(int contactId) throws ApplicationException {
        Contact contactForUpdata = null;
        try (Statement statement = connection.createStatement()) {
            statement.execute("select * from contacts where id = " + contactId);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                contactForUpdata = new Contact();
                contactForUpdata.setId(resultSet.getInt(1));
                contactForUpdata.setName(resultSet.getString(2));
                contactForUpdata.setSurName(resultSet.getString(3));
                contactForUpdata.setPhoneNumber(resultSet.getString(4));
                contactForUpdata.setAge(resultSet.getInt(5));
                contactForUpdata.setHeight(resultSet.getDouble(6));
                contactForUpdata.setMarried(resultSet.getBoolean(7));
                contactForUpdata.setCreateDate(resultSet.getString(8));
            }
            if (contactForUpdata == null) {
                throw new ApplicationException(ResponseCode.NOT_FOUND, MassageApp.THERE_IS_NOT_ID);
            } else {
                return contactForUpdata;
            }
        } catch (SQLException e) {
            throw new ApplicationException(ResponseCode.FAILED_GET_DB, MassageApp.FIELD_GET_DB);
        }
    }

    public void showContacts() throws ApplicationException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(SELECT_ALL);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println(
                        "ID: " + resultSet.getInt(1) + "  " +
                                "NAME: " + resultSet.getString(2) + "  " +
                                "SURNAME: " + resultSet.getString(3) + "  " +
                                "PHONE NUMBER: " + resultSet.getString(4) + "  " +
                                "AGE: " + resultSet.getInt(5) + "  " +
                                "HEIGHT: " + resultSet.getDouble(6) + "  " +
                                "MARRIED: " + resultSet.getBoolean(7) + "  " +
                                "CREATE DATE: " + resultSet.getString(8));
            }
            System.out.println("That's ALL!");
        } catch (SQLException e) {
            throw new ApplicationException(ResponseCode.FAILED_GET_DATA, MassageApp.FIELD_GET_DATA);
        }
    }

    @Override
    public void updateContactById(Contact contact) throws ApplicationException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATA_CONTACT)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurName());
            preparedStatement.setInt(4, contact.getAge());
            preparedStatement.setDouble(5, contact.getHeight());
            preparedStatement.setString(3, contact.getPhoneNumber());
            preparedStatement.setBoolean(6, contact.isMarried());
            preparedStatement.setInt(7, contact.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ApplicationException(ResponseCode.FAILED_UPDATE_DB, MassageApp.FIELD_UPDATE_DB);
        }
    }
}
