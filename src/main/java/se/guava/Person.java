package se.guava;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class Person implements Comparable<Person> {
    public String name;
    public Movie favMovie;

    @Override
    public int compareTo(final Person o) {
        return ComparisonChain.start().compare(name, o.name).compare(favMovie, o.favMovie, Ordering.natural().nullsFirst())
                .result();
    }

}
