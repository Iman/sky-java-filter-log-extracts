package uk.sky;

import java.io.*;
import java.util.Collection;

/**
 * Created by iman on 04/06/15.
 */

public class Main {

    public static void main(String[] args) throws IOException {

        //first method
        Collection<?> col = DataFilterer.filterByCountry(openFile("./multi-lines"), "DE");
        System.out.println(col);

        //second
        Collection<?> col2 = DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("./multi-lines"), "US", 539L);
        System.out.println(col2);
    }

    private static FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }
}
