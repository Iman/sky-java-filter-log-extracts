package uk.sky;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class DataFilterer {

    private static BufferedReader INPUT;
    private static String LINE;
    private static List<String> COLUMNS;
    private static int AVG = 30;
    private static List<List<String>> LINES;

    public static Collection<?> filterByCountry(Reader source, String country) throws IOException {

        try {
            INPUT = new BufferedReader(source);
        } catch (Exception e) {
            System.err.println("Error occuring while inpup is null");
        }
        String line;
        LINES = new ArrayList<List<String>>();
        boolean header = false;
        while ((line = INPUT.readLine()) != null) {
            if (header == false) {
                header = true;
                continue;
            } else if (line.contains(country)) {
                COLUMNS = extractLog(line);
                LINES.add(COLUMNS);
            }
        }
        return LINES;
    }

    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) throws IOException {

        try {
            INPUT = new BufferedReader(source);
        } catch (Exception e) {
            System.err.println("Error occuring while inpup is null");
        }

        LINES = new ArrayList<List<String>>();
        boolean header = false;
        while ((LINE = INPUT.readLine()) != null) {
            if (header == false) {
                header = true;
                continue;
            } else if (LINE.contains(country)) {
                COLUMNS = extractLog(LINE);
                if (Integer.parseInt(COLUMNS.get(2)) >= limit) {
                    LINES.add(COLUMNS);
                }
            }

        }
        return LINES;
    }

    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) throws NumberFormatException, IOException {
        try {
            INPUT = new BufferedReader(source);
        } catch (Exception e) {
            System.err.println("Error occuring while inpup is null");
        }
        LINES = new ArrayList<List<String>>();
        boolean header = false;
        while ((LINE = INPUT.readLine()) != null) {
            if (header == false) {
                header = true;
                continue;
            } else {
                COLUMNS = extractLog(LINE);
                if (Integer.parseInt(COLUMNS.get(2)) > AVG) {
                    LINES.add(COLUMNS);
                }
            }
        }
        return LINES;
    }

    /**
     * Extract request_timestamp, country code, response time
     *
     * @param line
     * @return
     */
    public static List<String> extractLog(String line) {
        List<String> strArray = new ArrayList<>();
        line += '\n';
        String word = "";
        for (int j = 0; j < line.length(); j++) {
            char c = line.charAt(j);
            if (c == ',' || c == '\n') {
                strArray.add(word);
                word = "";
            } else {
                word += c;
            }
        }

        return strArray;
    }
}