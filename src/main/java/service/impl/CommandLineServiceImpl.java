package service.impl;

import dao.impl.ContactDaoImpl;
import service.CommandLIneService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandLineServiceImpl implements CommandLIneService {

    private static final BufferedReader readerKeyboard = new BufferedReader(new InputStreamReader(System.in));
    private static final ContactServiceImpl service = new ContactServiceImpl(new ContactDaoImpl());

    public static void start() {
        CommandLIneService.run(readerKeyboard, service);
    }

}
