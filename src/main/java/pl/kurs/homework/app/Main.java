package pl.kurs.homework.app;

import pl.kurs.homework.model.Person;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Integer> list1 = List.of(5, 9, 45, 71, -5, 0, 11, 52, 3, 7, 31, -41, 2);
        System.out.println(getTopFiveElements(list1));

        List<Integer> list2 = List.of(1, 5, 4);
        System.out.println(getTopFiveElements(list2));

        List<Integer> list3 = new ArrayList<>();
        System.out.println(getTopFiveElements(list3));

        System.out.println("----------------------------");

        List<Person> personList = List.of(
                new Person("Adam", 25, true),
                new Person("Anna", 51, false),
                new Person("Edyta", 29, false),
                new Person("Jan", 43, true),
                new Person("Michał", 39, true),
                new Person("Marta", 60, false),
                new Person("Mariusz", 7, true)
        );

        System.out.println("Average age of men: " + getAverageAgeBySpecificProperty(personList, Person::isMale));
        System.out.println("Average age of people with names starting with 'M': " + getAverageAgeBySpecificProperty(personList, person -> person.getName().startsWith("M")));

    }

    public static List<Integer> getTopFiveElements(List<Integer> list) {
        return Optional.ofNullable(list)
                .map(l -> l.stream()
                        .sorted(Comparator.reverseOrder())
                        .limit(5)
                        .collect(Collectors.toList()))
                .orElseGet(Collections::emptyList);
    }

    public static double getAverageAgeBySpecificProperty(List<Person> personList, Predicate<Person> predicate) {
        OptionalDouble average = personList.stream()
                .filter(predicate)
                .mapToInt(Person::getAge)
                .average();
        return average.orElse(0);
    }
}
