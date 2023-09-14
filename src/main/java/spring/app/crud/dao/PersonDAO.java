package spring.app.crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.app.crud.models.Book;
import spring.app.crud.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        jdbcTemplate.update("INSERT INTO Person(name, birth_year) VALUES(?, ?)", person.getName(), person.getBornYear());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, birth_year=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getBornYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
                new BookMapper());
    }
}
