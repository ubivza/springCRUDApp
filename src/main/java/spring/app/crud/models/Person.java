package spring.app.crud.models;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Pattern(regexp = "[a-zA-Z]+", message = "Name can't contain numbers")
    private String name;
    @Min(value = 1, message = "Age can't be less than 1")
    private int age;
    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Address is not valid (Country, City, Postal code (6 digits)")
    private String address;

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
