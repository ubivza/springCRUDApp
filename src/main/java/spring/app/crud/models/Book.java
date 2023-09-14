package spring.app.crud.models;


import javax.validation.constraints.*;

public class Book {
    private int id;
    @NotEmpty(message = "Book name cant be empty")
    @Size(min = 1, max = 100, message = "Book name must be longer than 0")
    @Pattern(regexp = "[A-Z]\\w*", message = "Enter valid book name: Example")
    private String title;
    @NotEmpty(message = "Book author cant be empty")
    @Size(min = 1, max = 100, message = "Name must be longer than 0")
    @Pattern(regexp = "[A-Z]\\w* [A-Z]\\w*", message = "Valid name: Name Surname")
    private String author;
    @NotEmpty
    @Min(value = 1, message = "In library books may be only from our century")
    @Max(value = 2023, message = "There cant be books from future")
    private int year;

    public Book(int id, String bookName, String bookAuthor, int year) {
        this.id = id;
        this.title = bookName;
        this.author = bookAuthor;
        this.year = year;
    }

    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return title;
    }

    public void setBookName(String title) {
        this.title = title;
    }

    public String getBookAuthor() {
        return author;
    }

    public void setBookAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
