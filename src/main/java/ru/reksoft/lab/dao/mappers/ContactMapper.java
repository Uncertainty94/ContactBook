package ru.reksoft.lab.dao.mappers;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import ru.reksoft.lab.domain.Contact;


/**
 * Created by mishanin on 05.05.2016.
 */
public class ContactMapper implements RowMapper<Contact> {

    private final static String ID              = "id";
    private final static String NAME            = "name";
    private final static String SURNAME         = "surname";
    private final static String TEL_NUM         = "tel_number";
    private final static String MAIL            = "mail";
    private final static String ORGANIZATION    = "organization";
    private final static String POSITION        = "position";

    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Contact(
                rs.getInt(ID),
                rs.getString(NAME),
                rs.getString(SURNAME),
                rs.getString(TEL_NUM),
                rs.getString(MAIL),
                rs.getString(ORGANIZATION),
                rs.getString(POSITION));
    }

}
