import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class FileService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");
    private static final DateTimeFormatter YEAR_FORMATTER =

    public List<SalesRecord> read(String fileName) {
        List<SalesRecord> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            bufferedReader.readLine();
//            String line;
//
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length == 2) {
//                    String[] date = parts[0].split("-");
//                    YearMonth year = YearMonth.parse(date[1], YEAR_FORMATTER);
//                    list.add(new SalesRecord(yearMonth, Integer.parseInt(parts[1].trim())));
//                }
//            }
            list = bufferedReader.lines()
                    .map(line -> line.split(","))
                    .filter(parts -> parts.length == 2)
                    .map(date -> date[0].split("-"))
                    .map(parts -> new SalesRecord(YearMonth.parse(parts[0].trim()), Integer.parseInt(parts[1].trim())))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Oops, there was issue reading the file -> Issue " + e.getMessage());
        }

        return list;
    }

    // filter

    // print
}
