package ru.reksoft.lab.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by mishanin on 27.04.2016.
 */
public abstract class Provider {
    int ID              = 1;
    int NAME            = 2;
    int SURNAME         = 3;
    int TEL_NUM         = 4;
    int MAIL            = 5;
    int ORGANIZATION    = 6;
    int POSITION        = 7;

    String JDBC_DRIVER;
    String DB_URL;
    String TABLE_NAME = "contacts_table"
            ;

    String USER;
    String PASS;

    Provider(){
        try {
            loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    abstract public void addContact(String name, String surname, String telNumber, String mail, String organization, String position)
            throws SQLException;

    abstract public void deleteContact(int id) throws SQLException;

    abstract public void updateContact(Contact contact) throws SQLException;

    abstract public List<Contact> getContacts() throws SQLException;

    abstract public int getSizeOfBook() throws SQLException;

    private void loadProperties()throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(new File("server.properties")));
        JDBC_DRIVER = props.getProperty("JDBC_DRIVER");
        DB_URL = props.getProperty("DB_URL");
//        TABLE_NAME = props.getProperty("TABLE_NAME");
        USER = props.getProperty("USER");
        PASS = props.getProperty("PASS");

    }

}
