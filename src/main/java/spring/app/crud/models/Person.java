package spring.app.crud.models;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Pattern(regexp = "[a-zA-Z]+", message = "Name can't contain numbers")
    private String fullName;
    @Min(value = 1900, message = "Birth year should be greater than 1900")
    private int yearOfBirth;

    public Person(String fullName, int year) {
        this.fullName = fullName;
        this.yearOfBirth = year;
    }

    public Person() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
