package service.impl;

import dao.impl.ConnectionDB;
import dao.impl.ContactDaoImpl;
import service.CommandLIneService;

import java.io.*;

public class CommandLineServiceImpl implements CommandLIneService {

    private static final BufferedReader readerKeyboard = new BufferedReader(new InputStreamReader(System.in));
    private static final ContactServiceImpl service = new ContactServiceImpl(new ContactDaoImpl());

    public static void start() {
        ConnectionDB.connectAndCreateDataBase();
        CommandLIneService.run(readerKeyboard, service);
    }
}