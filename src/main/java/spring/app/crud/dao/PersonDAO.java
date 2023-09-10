package spring.app.crud.dao;

import org.springframework.stereotype.Component;
import spring.app.crud.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int peopleCount;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++peopleCount, "Ivan", 22, "vano812@mail.ru"));
        people.add(new Person(++peopleCount, "Aleksey", 41, "alex98@rambler.ru"));
        people.add(new Person(++peopleCount, "Ksenya", 33, "ksuha2007@gmail.com"));
        people.add(new Person(++peopleCount, "Varvara", 18, "prettyteen@mail.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        people.add(new Person(++peopleCount, person.getName(), person.getAge(), person.getEmail()));
    }

    public void update(int id, Person updatedPerson) {
        Person updateTo = show(id);

        updateTo.setName(updatedPerson.getName());
        updateTo.setAge(updatedPerson.getAge());
        updateTo.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
