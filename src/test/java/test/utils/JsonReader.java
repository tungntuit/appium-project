package test.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonReader {

    private static final String BASE_PATH = "src/test/resources/testdata/";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Map<String, String>> readFile(String fileName) {
        try {
            return mapper.readValue(
                new File(BASE_PATH + fileName),
                new TypeReference<>() {}
            );
        } catch (IOException e) {
            throw new RuntimeException("Không đọc được file: " + fileName, e);
        }
    }

    // Trả về Object[][] cho @DataProvider của TestNG
    public static Object[][] toDataProvider(String fileName) {
        List<Map<String, String>> rows = readFile(fileName);
        Object[][] result = new Object[rows.size()][1];
        for (int i = 0; i < rows.size(); i++) {
            result[i][0] = rows.get(i);
        }
        return result;
    }
}
