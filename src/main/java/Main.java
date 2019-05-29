import exception.ApplicationException;
import service.ContactService;
import service.impl.CommandLineServiceImpl;
import service.impl.ContactServiceImpl;

import java.io.IOException;
import java.sql.*;

public class Main {
//
//    public static final String jdbc = "jdbc:mysql:";
//    public static final String hostAndPort = "//localhost:3306";
//    public static final String url = "/address_book";
//    public static final String unicode = "useUnicode=true";
//    public static final String jdbcTimezoneShift = "useJDBCCompliantTimezoneShift=true";
//    public static final String dateTime = "useLegacyDatetimeCode=false";
//    public static final String serverTimeZone = "serverTimezone=UTC";
//    public static final String separator = "?";
//    public static final String separatorAnd = "&";
//    public static final String fullUrl =
//            jdbc
//                    + hostAndPort
//                    + url
//                    + separator
//                    + unicode
//                    + separatorAnd
//                    + jdbcTimezoneShift
//                    + separatorAnd
//                    + dateTime
//                    + separatorAnd
//                    + serverTimeZone;
//    public static final String user = "root";
//    public static final String password = "root";
//    public static final String insertPerson = "insert into person (name,age,married) VALUES (?,?,?)";


    public static void main(String[] args) throws IOException, SQLException {
//        Connection connection = DriverManager.getConnection(fullUrl, user, password);
////        Statement statement = connection.createStatement();
//        PreparedStatement statement = connection.prepareStatement(insertPerson);
////        statement.execute("create table if not exists person(id int primary key auto_increment, " +
////                "name varchar (255), " +
////                "married boolean default false)");
//        statement.setString(1, "Igor");
//        statement.setInt(2, 42);
//        statement.setBoolean(3,true);
//        statement.execute();
////        statement.execute("insert into person (id, name, married) value (2,'Timy', true)");
////        statement.execute("insert into person (id, name, married) value (2,'Timy', true)");
////        statement.execute("update person set name='Karl', married=true where id=2");
////        statement.execute("delete from person where name='Timy'");
////        statement.execute("alter table person add age int");
//
////        PreparedStatement preparedStatement = connection.prepareStatement(
////                "insert into person(name, married, age) value (?,?,?)");
////        preparedStatement.setString(1,"Slava");
////        preparedStatement.setBoolean(2, false);
////        preparedStatement.setInt(3, 34);
////        preparedStatement.execute();
//
//        connection.setAutoCommit(false);
//        statement.execute("select * from person");
//        connection.commit();
//
//        ResultSet resultSet = statement.getResultSet();
//        while (resultSet.next()) {
//            System.out.println(
//                    " id: " + resultSet.getInt(1) +
//                    " name: " + resultSet.getString(2) +
//                    " married: " + resultSet.getBoolean(3) +
//                    " age: " + resultSet.getInt(4));
//        }
//        statement.close();
//        connection.close();


        CommandLineServiceImpl.start();
    }
}