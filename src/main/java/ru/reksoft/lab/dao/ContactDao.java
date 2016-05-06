package ru.reksoft.lab.dao;

import ru.reksoft.lab.domain.Contact;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by mishanin on 27.04.2016.
 */
public interface ContactDao {

//    String JDBC_DRIVER;
//    String DB_URL;
    String TABLE_NAME = "contacts_table";

//    String USER;
//    String PASS;

//    public ContactDao(){
//        try {
//            loadProperties();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    void addContact(String name, String surname, String telNumber, String mail, String organization, String position)
            throws SQLException;

    void deleteContact(int id) throws SQLException;

    void updateContact(Contact contact) throws SQLException;

    List<Contact> getContacts() throws SQLException;

    int getSizeOfBook() throws SQLException;

//    private void loadProperties()throws IOException {
//        Properties props = new Properties();
//        props.load(new FileInputStream(new File("server.properties")));
//        JDBC_DRIVER = props.getProperty("JDBC_DRIVER");
//        DB_URL = props.getProperty("DB_URL");
////        TABLE_NAME = props.getProperty("TABLE_NAME");
//        USER = props.getProperty("USER");
//        PASS = props.getProperty("PASS");
//
//    }

}
