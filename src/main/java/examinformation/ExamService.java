package examinformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ExamService {

    private int theoryMax;
    private int practiceMax;
    private Map<String, ExamResult> results = new TreeMap<>();

    public void readFromFIle(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            getMaxValues(br.readLine());
            while (br.ready()) {
                addResult(br.readLine());
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file: " + path);
        } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException exc) {
            throw new IllegalArgumentException("Invalid row in the file!");
        }
    }

    private void getMaxValues(String firstLine) {
        String[] firstRow = firstLine.split(" ");
        theoryMax = Integer.parseInt(firstRow[0]);
        practiceMax = Integer.parseInt(firstRow[1]);
    }

    private void addResult(String line) {
        String[] row = line.split(";");
        String[] result = row[1].split(" ");
        results.put(row[0], new ExamResult(Integer.parseInt(result[0]), Integer.parseInt(result[1])));
    }

    public List<String> findPeopleFailed() {
        validateResults();
        return results.entrySet().stream()
                .filter(result -> !result.getValue().areBothSuccess(theoryMax, practiceMax))
                .map(Map.Entry::getKey)
                .toList();
    }

    public String findBestPerson() {
        validateResults();
        return results.entrySet().stream()
                .filter(result -> result.getValue().areBothSuccess(theoryMax, practiceMax))
                .max(Comparator.comparing(o -> o.getValue().getSumOfPoints()))
                .orElseThrow(() -> new IllegalArgumentException("No such result")).getKey();
    }

    private void validateResults() {
        if (results.size() <= 0) {
            throw new IllegalArgumentException("Empty map!");
        }
    }

    public int getTheoryMax() {
        return theoryMax;
    }

    public int getPracticeMax() {
        return practiceMax;
    }

    public Map<String, ExamResult> getResults() {
        return Map.copyOf(results);
    }
}
