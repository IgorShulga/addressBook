import entity.Contact;
import exception.ApplicationException;
import service.impl.CommandLineServiceImpl;

public class Main {

    public static void main(String[] args) throws ApplicationException {
        CommandLineServiceImpl.start();
    }
}