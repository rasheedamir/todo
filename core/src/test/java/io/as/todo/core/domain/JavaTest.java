package io.as.todo.core.domain;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.Assert.assertEquals;

public class JavaTest
{
    public static void main(String[] args)
    {
        Map<String, List<String>> people = new HashMap<>();
        people.put("John", asList("555-1123", "555-3389"));
        people.put("Mary", asList("555-2243", "555-5264"));
        people.put("Steve", asList("555-6654", "555-3242"));

        List<String> phones = people
            .values()
            .stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    @Test
    public void convertStringToUpperCaseStreams()
    {
        List<String> collected = Stream
            .of("a", "b", "hello") // Stream of String
            .map(String::toUpperCase) // Returns a stream consisting of the results of applying the given function to the elements of this stream.
            .collect(Collectors.toList());

        assertEquals(asList("A", "B", "HELLO"), collected);
    }

    @Test
    public void test_flatMap() throws Exception
    {
        List<Integer> together = Stream
            .of(asList(1, 2), asList(3, 4)) // Stream of List<Integer>
            .flatMap(List::stream)
            .map(integer -> integer + 1)
            .collect(Collectors.toList());

        assertEquals(asList(2, 3, 4, 5), together);
    }

    @Test
    public void test_flatMap2() throws Exception
    {
        String[] arrayOfWords = {"STACK", "OOOOVVVER"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        List<String> chars = streamOfWords
            .map(s -> s.split(""))
            .flatMap(Arrays::stream)
            .distinct()
            .collect(Collectors.toList());

        String word = chars.stream().map(e -> e.toString()).reduce((acc, e) -> acc + e).get();

        System.out.println(chars.toString());
        System.out.println(word);

        String str = chars.stream().map(e->e.toString()).collect(Collectors.joining());
        System.out.println(str);
    }

    @Test
    public void testJava8Stream() throws Exception
    {
        List<String> myList =
            Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList
            .stream()
            .filter(s -> s.startsWith("c"))
            .map(String::toUpperCase)
            .sorted()
            .forEach(System.out::println);

        System.out.println(System.lineSeparator());

        Arrays.asList("a1", "a2", "a3")
            .stream()
            .findFirst()
            .ifPresent(System.out::println);

        System.out.println(System.lineSeparator());

        Stream.of("a1", "a2", "a3")
            .findFirst()
            .ifPresent(System.out::println);

        System.out.println(System.lineSeparator());

        IntStream.range(1, 4)
            .forEach(System.out::println);

        System.out.println(System.lineSeparator());

        Arrays.stream(new int[] {1, 2, 3})
            //.map(n -> 2 * n + 1)
            .average()
            .ifPresent(System.out::println);

        System.out.println(System.lineSeparator());

        Stream.of("a1", "a2", "a3")
            .map(s -> s.substring(1))   // "1", "2", "3"
            .mapToInt(Integer::parseInt) // 1, 2, 3
            .max() // 3
            .ifPresent(System.out::println); // 3

        System.out.println(System.lineSeparator());

        IntStream.range(1, 4)
            .mapToObj(i -> "a" + i)
            .forEach(System.out::println);

        // a1
        // a2
        // a3

        System.out.println(System.lineSeparator());

        Stream.of(1.0, 2.0, 3.0)
            .mapToInt(Double::intValue)
            .mapToObj(i -> "a" + i)
            .forEach(System.out::println);

        // a1
        // a2
        // a3

        System.out.println(System.lineSeparator());

        Stream.of("d2", "a2", "b1", "b3", "c")
            .filter(s -> {
                System.out.println("filter: " + s);
                return true;
            });

        System.out.println(System.lineSeparator());

        Stream.of("d2", "a2", "b1", "b3", "c")
            .filter(s -> {
                System.out.println("filter: " + s);
                return true;
            })
            .forEach(s -> System.out.println("forEach: " + s));

        System.out.println(System.lineSeparator());

        Stream.of("d2", "a2", "b1", "b3", "c")
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .anyMatch(s -> {
                System.out.println("anyMatch: " + s);
                return s.startsWith("A");
            });

        // map:      d2
        // anyMatch: D2
        // map:      a2
        // anyMatch: A2

        System.out.println(System.lineSeparator());

        Stream.of("d2", "a2", "b1", "b3", "c")
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .filter(s -> {
                System.out.println("filter: " + s);
                return s.startsWith("A");
            })
            .forEach(s -> System.out.println("forEach: " + s));

        // map:     d2
        // filter:  D2
        // map:     a2
        // filter:  A2
        // forEach: A2
        // map:     b1
        // filter:  B1
        // map:     b3
        // filter:  B3
        // map:     c
        // filter:  C

        System.out.println(System.lineSeparator());

        Stream.of("d2", "a2", "b1", "b3", "c")
            .filter(s -> {
                System.out.println("filter: " + s);
                return s.startsWith("a");
            })
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .forEach(s -> System.out.println("forEach: " + s));

        // filter:  d2
        // filter:  a2
        // map:     a2
        // forEach: A2
        // filter:  b1
        // filter:  b3
        // filter:  c

        System.out.println(System.lineSeparator());

        Stream.of("d2", "a2", "b1", "b3", "c")
            .sorted((s1, s2) -> {
                System.out.printf("sort: %s; %s\n", s1, s2);
                return s1.compareTo(s2);
            })
            .filter(s -> {
                System.out.println("filter: " + s);
                return s.startsWith("a");
            })
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .forEach(s -> System.out.println("forEach: " + s));

        System.out.println(System.lineSeparator());

        Stream.of("d2", "a2", "b1", "b3", "c")
            .filter(s -> {
                System.out.println("filter: " + s);
                return s.startsWith("a");
            })
            .sorted((s1, s2) -> {
                System.out.printf("sort: %s; %s\n", s1, s2);
                return s1.compareTo(s2);
            })
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .forEach(s -> System.out.println("forEach: " + s));

            // filter:  d2
            // filter:  a2
            // filter:  b1
            // filter:  b3
            // filter:  c
            // map:     a2
            // forEach: A2

        System.out.println(System.lineSeparator());

        Supplier<Stream<String>> streamSupplier =
            () -> Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok

        System.out.println(System.lineSeparator());

        List<Person> persons =
            Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));

        Map<Integer, List<Person>> personsByAge = persons
            .stream()
            .collect(groupingBy(p -> p.age));

        personsByAge
            .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

        System.out.println(System.lineSeparator());

        Double averageAge = persons
            .stream()
            .collect(Collectors.averagingInt(p -> p.age));

        System.out.println(averageAge);     // 19.0

        System.out.println(System.lineSeparator());

        String phrase = persons
            .stream()
            .filter(p -> p.age >= 18)
            .map(p -> p.name)
            .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase);

        System.out.println(System.lineSeparator());

        String phrase2 = persons
            .stream()
            .filter(hasLegalAge())
            .map(nameOnly())
            .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase2);
        // In Germany Max and Peter and Pamela are of legal age.

        System.out.println(System.lineSeparator());

        Map<Integer, String> map = persons
            .stream()
            .collect(Collectors.toMap(
                p -> p.age,
                p -> p.name,
                (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);
        // {18=Max, 23=Peter;Pamela, 12=David}

        System.out.println(System.lineSeparator());

        Collector<Person, StringJoiner, String> personNameCollector =
            Collector.of(
                () -> new StringJoiner(" | "),          // supplier
                (j, p) -> j.add(p.name.toUpperCase()),  // accumulator
                (j1, j2) -> j1.merge(j2),               // combiner
                StringJoiner::toString);                // finisher

        String names = persons
            .stream()
            .collect(personNameCollector);

        System.out.println(names);  // MAX | PETER | PAMELA | DAVID

        System.out.println(System.lineSeparator());

        List<Foo> foos = new ArrayList<>();

        // create foos
        IntStream
            .range(1, 4)
            .forEach(i -> foos.add(new Foo("Foo" + i)));

        // create bars
        foos.forEach(f ->
            IntStream
                .range(1, 4)
                .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));

        foos.stream()
            .flatMap(bars())
            .forEach(printBar());

        // Bar1 <- Foo1
        // Bar2 <- Foo1
        // Bar3 <- Foo1
        // Bar1 <- Foo2
        // Bar2 <- Foo2
        // Bar3 <- Foo2
        // Bar1 <- Foo3
        // Bar2 <- Foo3
        // Bar3 <- Foo3

        System.out.println(System.lineSeparator());

        IntStream.range(1, 4)
            .mapToObj(i -> new Foo("Foo" + i))
            .peek(f -> IntStream.range(1, 4)
                .mapToObj(i -> new Bar("Bar" + i + " <- " + f.name))
                .forEach(f.bars::add))
            .flatMap(f -> f.bars.stream())
            .forEach(b -> System.out.println(b.name));

        System.out.println(System.lineSeparator());

        Optional.of(new Outer())
            .flatMap(o -> Optional.ofNullable(o.nested))
            .flatMap(n -> Optional.ofNullable(n.inner))
            .flatMap(i -> Optional.ofNullable(i.foo))
            .ifPresent(System.out::println);

        System.out.println(System.lineSeparator());

        persons
            .stream()
            .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
            .ifPresent(System.out::println);    // Pamela

        System.out.println(System.lineSeparator());

        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());    // 3

        System.out.println(System.lineSeparator());

        Arrays.asList("a1", "a2", "b1", "c2", "c1")
            .parallelStream()
            .filter(s -> {
                System.out.format("filter: %s [%s]\n",
                    s, Thread.currentThread().getName());
                return true;
            })
            .map(s -> {
                System.out.format("map: %s [%s]\n",
                    s, Thread.currentThread().getName());
                return s.toUpperCase();
            })
            .forEach(s -> System.out.format("forEach: %s [%s]\n",
                s, Thread.currentThread().getName()));

        System.out.println(System.lineSeparator());

        Arrays.asList("a1", "a2", "b1", "c2", "c1")
            .parallelStream()
            .filter(s -> {
                System.out.format("filter: %s [%s]\n",
                    s, Thread.currentThread().getName());
                return true;
            })
            .map(s -> {
                System.out.format("map: %s [%s]\n",
                    s, Thread.currentThread().getName());
                return s.toUpperCase();
            })
            .sorted((s1, s2) -> {
                System.out.format("sort: %s <> %s [%s]\n",
                    s1, s2, Thread.currentThread().getName());
                return s1.compareTo(s2);
            })
            .forEach(s -> System.out.format("forEach: %s [%s]\n",
                s, Thread.currentThread().getName()));

        System.out.println(System.lineSeparator());

        /*
        Path path = Paths.get("");
        Map<String, Long> wordCount = Files
            .lines(path)
            .parallel()
            .flatMap(line -> Arrays.stream(line.trim().split("\\s")))
            .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())
            .filter(word -> word.length() > 0)
            .map(word -> new AbstractMap.SimpleEntry<>(word, 1))
            .collect(groupingBy(AbstractMap.SimpleEntry::getKey, counting()));
        wordCount.forEach((k, v) -> System.out.println(String.format("%s ==>> %d", k, v)));
        */

        System.out.println(System.lineSeparator());
    }

    Consumer<Bar> printBar()
    {
        return bar -> System.out.println(bar.name);
    }

    Function<Foo, Stream<Bar>> bars()
    {
        return foo -> foo.bars.stream();
    }

    Predicate<Person> hasLegalAge()
    {
        return person -> person.age >= 18;
    }

    Function<Person, String> nameOnly()
    {
        return person -> person.name;
    }

    class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    class Foo
    {
        String name;
        List<Bar> bars = new ArrayList<>();

        Foo(String name)
        {
            this.name = name;
        }
    }

    class Bar
    {
        String name;

        Bar(String name)
        {
            this.name = name;
        }
    }

    class Outer
    {
        Nested nested;
    }

    class Nested
    {
        Inner inner;
    }

    class Inner
    {
        String foo;
    }
}
