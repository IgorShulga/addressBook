import exception.ApplicationException;
import service.ContactService;
import service.impl.CommandLineServiceImpl;
import service.impl.ContactServiceImpl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ApplicationException, IOException {
        CommandLineServiceImpl.start();
    }
}