package service.impl;

import dao.impl.ContactDaoImpl;
import service.CommandLIneService;

import java.io.*;

public class CommandLineServiceImpl implements CommandLIneService {

    private static final BufferedReader readerKeyboard = new BufferedReader(new InputStreamReader(System.in));
    private static final ContactServiceImpl service = new ContactServiceImpl(new ContactDaoImpl());

    public static void start() throws IOException {
        service.checkCreateAndReadFile();
        CommandLIneService.run(readerKeyboard, service);
        CommandLineServiceImpl.service.writeFromSetToFile(service.getStoreForWrite());
    }
}