package se.guava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ranges;
import com.google.common.io.BaseEncoding;
import com.google.common.io.Resources;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

public class GuavaTest {
    @Test
    public void testCharMatcher() throws Exception {
        assertTrue(CharMatcher.DIGIT.or(CharMatcher.inRange('a', 'c')).matchesAllOf("abc123"));
        assertTrue(CharMatcher.WHITESPACE.matchesNoneOf("___"));
        assertEquals("åäö123", CharMatcher.JAVA_LETTER_OR_DIGIT.retainFrom("åäö - 123"));
    }

    @Test
    public void filtering() throws Exception {
        final List<Integer> list12345 = ImmutableList.of(1, 2, 3, 4, 5);
        final Collection<Integer> list234 = Collections2.filter(list12345, Ranges.closed(2, 4));
        assertTrue(list234.size() == 3);
        assertTrue(list234.containsAll(ImmutableList.of(2, 3, 4)));
    }

    @Test
    public void filteringStd() throws Exception {
        final List<Integer> list12345 = Arrays.asList(1, 2, 3, 4, 5);

        final Collection<Integer> list234 = new ArrayList<>();
        for (final Integer value : list12345) {
            if (value >= 2 && value <= 4) {
                list234.add(value);
            }
        }

        assertTrue(list234.size() == 3);
        assertTrue(list234.containsAll(ImmutableList.of(2, 3, 4)));
    }

    @Test
    public void transform() throws Exception {
        final List<Long> longList = Longs.asList(1, 2, 3, 4, 5);

        final Function<Long, Integer> longToIntTransformer = new Function<Long, Integer>() {
            @Override
            public Integer apply(final Long value) {
                return Ints.checkedCast(value);
            }
        };
        final List<Integer> intList = Lists.transform(longList, longToIntTransformer);
    }

    @Test
    public void transformStd() throws Exception {
        final List<Long> longList = Longs.asList(1, 2, 3, 4, 5);

        final List<Integer> intList = new ArrayList<>();
        for (final Long value : longList) {
            if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
                throw new IllegalArgumentException();
            }
            intList.add(value.intValue());
        }

    }

    @Test
    public void encode() throws Exception {
        final byte[] bytes = "foo".getBytes(Charsets.UTF_8);
        BaseEncoding.base64().encode(bytes); // since Guava 14.0
    }

    @Test
    public void resources() throws Exception {
        final List<String> lines = Resources.readLines(Resources.getResource("log4j.xml"), Charsets.UTF_8);
    }
}
