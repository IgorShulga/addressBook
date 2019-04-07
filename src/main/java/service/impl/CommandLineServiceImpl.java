package service.impl;

import dao.impl.ContactDaoImpl;
import service.CommandLIneService;

import java.util.Scanner;

public class CommandLineServiceImpl implements CommandLIneService {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ContactServiceImpl service = new ContactServiceImpl(new ContactDaoImpl());


    public static void start() {
        CommandLIneService.run(scanner, service);
    }

}
