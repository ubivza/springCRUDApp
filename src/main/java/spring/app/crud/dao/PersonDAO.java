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
        people.add(new Person(++peopleCount, "Ivan"));
        people.add(new Person(++peopleCount, "Aleksey"));
        people.add(new Person(++peopleCount, "Ksenya"));
        people.add(new Person(++peopleCount, "Varvara"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        people.add(new Person(++peopleCount, person.getName()));
    }

    public void update(int id, Person updatedPerson) {
        Person updateTo = show(id);

        updateTo.setName(updatedPerson.getName());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
