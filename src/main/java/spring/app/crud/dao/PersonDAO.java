package spring.app.crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.app.crud.models.Person;

import java.sql.*;
import java.util.ArrayList;
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

    public Person show(int id) {
        Person person = null;

        try {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM Person WHERE id=?");

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            person = new Person();

            person.setAge(resultSet.getInt("age"));
            person.setName(resultSet.getString("name"));
            person.setId(resultSet.getInt("id"));
            person.setEmail(resultSet.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void save(Person person) {

        try {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO Person VALUES(1, ?, ?, ?)");
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");
            statement.setString(1, updatedPerson.getName());
            statement.setInt(2, updatedPerson.getAge());
            statement.setString(3, updatedPerson.getEmail());
            statement.setInt(4, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM Person WHERE id=?");

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
