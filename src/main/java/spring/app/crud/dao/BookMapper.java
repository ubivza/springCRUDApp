package spring.app.crud.dao;

import org.springframework.jdbc.core.RowMapper;
import spring.app.crud.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setBookName(resultSet.getString("book_name"));
        book.setBookAuthor(resultSet.getString("author_name"));
        book.setYear(resultSet.getInt("year"));
        return book;
    }
}
