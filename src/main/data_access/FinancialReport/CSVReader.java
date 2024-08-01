package data_access.FinancialReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading CSV files.
 * Author: Chi Fong Huang
 */
public class CSVReader {

    /**
     * Reads the CSV file from the given file path.
     *
     * @param filePath the path of the CSV file.
     * @return a list of records, each record is an array of strings.
     * @throws IOException if an I/O error occurs.
     */
    public static List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
        }
        return records;
    }
}

