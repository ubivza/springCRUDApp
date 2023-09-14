package spring.app.crud.models;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Pattern(regexp = "[a-zA-Z]+", message = "Name can't contain numbers")
    private String name;
    @Min(value = 1900, message = "Birth year should be greater than 1900")
    private int bornYear;

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.bornYear = year;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBornYear() {
        return bornYear;
    }

    public void setBornYear(int bornYear) {
        this.bornYear = bornYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
