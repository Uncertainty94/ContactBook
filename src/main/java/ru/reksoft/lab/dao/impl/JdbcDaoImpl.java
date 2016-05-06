package ru.reksoft.lab.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.reksoft.lab.dao.ContactDao;
import ru.reksoft.lab.dao.mappers.ContactMapper;
import ru.reksoft.lab.domain.Contact;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by mishanin on 05.05.2016.
 */
public class JdbcDaoImpl implements ContactDao {

    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void addContact(String name, String surname, String telNumber, String mail, String organization, String position) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (name,surname,tel_number,mail,organization,position) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplateObject.update( sql, name, surname, telNumber, mail, organization, position);
    }

    @Override
    public void deleteContact(int id) throws SQLException {
        String sql = "delete from " + TABLE_NAME + " where id = ?";
        jdbcTemplateObject.update(sql, id);
    }

    @Override
    public void updateContact(Contact contact) throws SQLException {

    }

    @Override
    public List<Contact> getContacts() throws SQLException {
        String sql = "select * from " + TABLE_NAME;
        List <Contact> contacts = jdbcTemplateObject.query(sql,
                new ContactMapper());
        return contacts;
    }

    @Override
    public int getSizeOfBook() throws SQLException {
        String sql = "SELECT count(*) as c FROM "+TABLE_NAME +" ;";
        int count = jdbcTemplateObject.queryForObject(sql, Integer.class);
        return count;
    }
}
