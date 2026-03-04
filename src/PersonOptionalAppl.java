import java.util.List;
import java.util.Optional;


public class PersonOptionalAppl {


    public static void main(String[] args) {
        List<Person> persons = List.of(new Person("name1", 30), new Person("name2", 40), new Person("name3", 30));
        Optional<Person> res = findPersonByAge(persons, 30);
        if (res.isPresent()) {
            Person person = res.get();
            System.out.println(person.getName());
        }
        Optional<Person> res1 = findPersonByAge(persons, 20);
        if (res1.isPresent()) {
            Person person1 = res1.get();
            System.out.println(person1.getName());
        }
        res1.ifPresent(p -> System.out.println(p.getName()));
        res.ifPresent(p -> System.out.println(p.getName()));
        Person p1 = res1.orElse(new Person("Anonumous", -1));
        System.out.println(p1);
        Person p = res.orElse(new Person("Anonumous", -1));
        System.out.println(p);
        Person pp = res.orElseGet(() -> new Person("Anonumous", -1));
        Person pp1 = res1.orElseThrow(()->new IllegalArgumentException());
        Person pp2 = res1.orElseThrow(IllegalArgumentException::new);
    }


    private static Optional<Person> findPersonByAge(List<Person> persons, int i) {
        Person res = null;
        for (Person person : persons) {
            if (person.getAge() == i)
                res = person;
            break;
        }
        return Optional.ofNullable(res);
    }


}
