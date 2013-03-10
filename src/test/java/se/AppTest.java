package se;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void testName() throws Exception {
        Assert.assertTrue(App.getHelloMessage().startsWith("Hello"));
    }
}
