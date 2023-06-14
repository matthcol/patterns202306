package iterator;

import org.junit.jupiter.api.Test;

import java.util.*;

public class IteratorExamples {

    @Test
    void iteratorList() {
        List<String> cities = List.of("Toulouse", "Pau", "Marseille", "Lyon", "Paris");
        for (var city: cities) {
            System.out.println(" - " + city);
        }
//        String city = cities.get(0);
//        for (var letter: city){} // Error: String is not Iterable

        String[] cityArray = cities.toArray(new String[0]);
        System.out.println();
        for (var city: cityArray) {
            System.out.println(" * " + city);
        }

        NavigableSet<String> citySet = new TreeSet<>(cities);
        System.out.println();
        for (var city: citySet) {
            System.out.println(" # " + city);
        }

        Comparator<String> cmp = String::compareToIgnoreCase;
        NavigableSet<String> citySet2 = new TreeSet<>(cmp.reversed());
        citySet2.addAll(citySet);
        citySet2.add("rennes");
        System.out.println();
        for (var city: citySet2) {
            System.out.println(" ~ " + city);
        }

        System.out.println();
        citySet2.forEach(System.out::println);

        Iterator<String> it = cities.iterator();
        System.out.println();
        while (it.hasNext()){
            var city = it.next();
            System.out.println(" @ " + city);
            // it.remove(); // java.lang.UnsupportedOperationException
        }

        Iterator<String> it1 = cities.iterator();
        Iterator<String> it2 = Arrays.asList(cityArray).iterator();
        boolean same = true;
        while (it1.hasNext() && it2.hasNext()) {
            var city1 = it1.next();
            var city2 = it2.next();
            if (city1 != city2) {
                same = false;
                break;
            }
        }
        System.out.println("Collections are identical:" + same);

        System.out.println();
        for (var it4 = citySet2.iterator(); it4.hasNext();) {
            var city = it4.next();
            if (city.startsWith("P")) {
                System.out.println("Remove:" + city);
                it4.remove();
            }
        }
        System.out.println(citySet2);
    }

    static <T> void printlnIterable(Iterable<T> iterable, String bullet) {
//        for (var it = iterable.iterator(); it.hasNext();) {
//            System.out.println(bullet + it.next());
//        }
        for (var city: iterable) {
            System.out.println(bullet + city);
        }
        // NB:
        var it  = iterable.iterator();
        System.out.println("Iterator class:" + it.getClass());
    }

    @Test
    void iteratorContainers2() {
        List<String> cities = List.of("Toulouse", "Pau", "Marseille", "Lyon", "Paris");
        printlnIterable(cities, " - ");

        String[] cityArray = cities.toArray(new String[0]);
        System.out.println();
        printlnIterable(Arrays.asList(cityArray), " * ");

        NavigableSet<String> citySet = new TreeSet<>(cities);
        System.out.println();
        printlnIterable(citySet, " # ");

        Comparator<String> cmp = String::compareToIgnoreCase;
        NavigableSet<String> citySet2 = new TreeSet<>(cmp.reversed());
        citySet2.addAll(citySet);
        citySet2.add("rennes");
        System.out.println();
        printlnIterable(citySet2, " ~ ");
    }

}
