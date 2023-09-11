package spring.app.crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.app.crud.models.Person;

import java.sql.*;
import java.util.List;

@Component
public class PersonDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    // если поля таблицы совпадают по названию с полями объекта можно использовать new BeanPropertyRowMapper<>(
                                    //Person.class)
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {

        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)", person.getName(), person.getAge(),
            person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
