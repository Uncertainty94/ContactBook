package ru.reksoft.lab.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mishanin on 27.04.2016.
 */
public class JdbcDAO implements Provider{

    private Connection conn = null;
    private Statement stmt = null;

    public JdbcDAO() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            Class.forName(JDBC_DRIVER);
            String sql = "SELECT exists(\n" +
                    "        SELECT 1\n" +
                    "        FROM information_schema.tables\n" +
                    "        WHERE table_catalog = current_catalog AND\n" +
                    "              table_schema = 'public' AND\n" +
                    "              table_name = '"+TABLE_NAME+"'\n" +
                    "    );";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            if ("f".equals(rs.getString(1))){
                sql = "CREATE TABLE "+TABLE_NAME+"(" +
                        " id serial primary key," +
                        " name VARCHAR(25) not null," +
                        " surname VARCHAR(25) not null," +
                        " tel_number VARCHAR(15) not null," +
                        " mail VARCHAR(40) not null," +
                        " organization VARCHAR(40) not null," +
                        " position VARCHAR(40) not null);";
                stmt.executeUpdate(sql);
            }

        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void addContact(String name, String surname, String telNumber, String mail, String organization, String position) throws SQLException {

        String sql = "INSERT INTO " + TABLE_NAME + " (name,surname,tel_number,mail,organization,position) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, surname);
        ps.setString(3, telNumber);
        ps.setString(4, mail);
        ps.setString(5, organization);
        ps.setString(6, position);

        ps.executeUpdate();
        ps.close();
    }

    @Override
    public List<Contact> getContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();

        String sql = "SELECT * FROM "+TABLE_NAME +" ;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            contacts.add(new Contact(rs.getInt(ID),
                                     rs.getString(NAME),
                                     rs.getString(SURNAME),
                                     rs.getString(TEL_NUM),
                                     rs.getString(MAIL),
                                     rs.getString(ORGANIZATION),
                                     rs.getString(POSITION)));
        }

        return contacts;
    }

    @Override
    public void deleteContact(int id) throws SQLException {
        String sql = "DELETE FROM "+TABLE_NAME +" WHERE id="+id+";";
        stmt.executeUpdate(sql);
    }

    @Override
    public void updateContact(Contact contact) throws SQLException {

    }

    @Override
    public int getSizeOfBook() throws SQLException {
        String sql = "SELECT count(*) FROM "+TABLE_NAME +" ;";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        return rs.getInt(1);
    }
}
