package uk.sky;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

@SuppressWarnings("unchecked")
public class DataFiltererTest {

    /**
     * @throws FileNotFoundException
     */
    @Test
    public void whenEmpty() throws FileNotFoundException {
        try {
            DataFilterer.filterByCountry(openFile("./empty"), "GB").isEmpty();
        } catch (IOException e) {
            Assert.assertTrue(e instanceof FileNotFoundException);
        }
    }

    InputStream inputStream = null;

    @Before
    public void doBefore() throws FileNotFoundException {
        inputStream = new FileInputStream("./multi-lines");
    }

    /**
     * @throws IOException
     */
    @Test
    public void shouldCheckFilterByCountry() throws IOException {
        List<String> collection = (List<String>) DataFilterer.filterByCountry(new InputStreamReader(inputStream), "US");
        Assert.assertNotNull(collection);
        System.out.println(collection);
    }

    /**
     * test - 2
     *
     * @throws IOException
     */
    @Test
    public void shouldCheckFilterByCountryWithResponseTimeAboveLimit() throws IOException {
        List<String> collection = (List<String>) DataFilterer.filterByCountryWithResponseTimeAboveLimit(new InputStreamReader(inputStream), "US", 539L);
        Assert.assertNotNull(collection);
        System.out.println(collection);
    }

    /**
     * @throws IOException
     */
    @Test
    public void shouldCheckFilterByResponseTimeAboveAverage() throws IOException {
        List<String> collection = (List<String>) DataFilterer.filterByResponseTimeAboveAverage(new InputStreamReader(inputStream));
        Assert.assertNotNull(collection);
        System.out.println(collection);
    }

    @Test
    public void shouldHandleExceptionCatch() {
        try {
            List<String> collection = (List<String>) DataFilterer.filterByCountryWithResponseTimeAboveLimit(null, null, 100L);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }
}
