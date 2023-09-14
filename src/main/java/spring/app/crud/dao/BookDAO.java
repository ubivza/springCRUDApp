package spring.app.crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
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
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    // если поля таблицы совпадают по названию с полями объекта можно использовать new BeanPropertyRowMapper<>(
    //Person.class)
    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BookMapper())
                .stream().findAny().orElse(null);
    }


    public void save(Book book) {

        jdbcTemplate.update("INSERT INTO Book(book_name, author_name, year) VALUES(?, ?, ?)",
                book.getBookName(), book.getBookAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET book_name=?, author_name=?, year=? WHERE id=?",
                updatedBook.getBookName(), updatedBook.getBookAuthor(), updatedBook.getYear(), id);
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
