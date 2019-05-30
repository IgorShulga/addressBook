package service.impl;

import dao.impl.ContactDaoImpl;
import service.CommandLIneService;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CommandLineServiceImpl implements CommandLIneService {

    private static final BufferedReader readerKeyboard = new BufferedReader(new InputStreamReader(System.in));
    private static final ContactServiceImpl service = new ContactServiceImpl(new ContactDaoImpl());

    public static void start() {

        try (Connection connection = DriverManager.getConnection(FULL_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) {
            System.out.println("NO DATABASE CONNECTION");
            e.printStackTrace();
        }
        CommandLIneService.run(readerKeyboard, service);
    }
}