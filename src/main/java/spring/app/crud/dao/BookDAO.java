package spring.app.crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.app.crud.models.Book;
import spring.app.crud.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    // если поля таблицы совпадают по названию с полями объекта можно использовать new BeanPropertyRowMapper<>(
    //Book.class)
    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }


    public void save(Book book) {

        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }


    public Optional<Person> showBookHolder(int id) {
        return jdbcTemplate.query("SELECT * FROM Person JOIN Book ON Person.id=Book.person_id WHERE Book.id=?",
                new Object[]{id}, new PersonMapper()).stream().findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?",
                id);
    }

    public void makeBookBelongTo(int id, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?",
                personId, id);
    }
}
