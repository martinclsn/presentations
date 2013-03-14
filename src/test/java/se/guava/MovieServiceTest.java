package se.guava;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class MovieServiceTest {

    public void measure() {
        new MovieService().measure();
    }

    @Test
    public void strings() throws Exception {
        final String string = "string";
        if (string == null || string.isEmpty()) {
            // do stuff
        }

        if (Strings.isNullOrEmpty(string)) {
            // do stuff
        }

        final boolean b = string == null || string.isEmpty();

        Assert.assertEquals(string, Strings.nullToEmpty(string));
        Strings.padEnd(string, 10, ' ');
        final List<?> list = null;
        final int count = 0;
        Preconditions.checkArgument(list != null, "List must not be null");
        Preconditions.checkArgument(count > 0, "must be positive: %s", count);
        // string == null ? "" : string

        // if (list == null) {
        // throw new IllegalArgumentException("List must not be null");
        // }
        // if (count <= 0) {
        // throw new IllegalArgumentException("must be positive: " + count);
        // }
    }
}
