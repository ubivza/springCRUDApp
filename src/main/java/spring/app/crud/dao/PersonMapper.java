package spring.app.crud.dao;


import org.springframework.jdbc.core.RowMapper;
import spring.app.crud.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setAge(resultSet.getInt("age"));
        person.setName(resultSet.getString("name"));
        person.setId(resultSet.getInt("id"));
        person.setEmail(resultSet.getString("email"));
        return person;
    }
}
