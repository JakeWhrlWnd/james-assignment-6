import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public List<String> read(String fileName) {
        List<String> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            bufferedReader.readLine();
            String line;
            while((line = bufferedReader.readLine()) != null) {

            }
        } catch (IOException e) {
            System.out.println("Oops, there was issue reading the file -> Issue " + e.getMessage());
        }

        return list;
    }
}
