package test.testdata;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class TestDataProvider {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String BASE_PATH = "src/test/resources/testdata/";

    public static <T> Object[][] readJson(String fileName, Class<T> modelClass) {
        try {
            File file = new File(BASE_PATH + fileName);
            List<T> dataList = mapper.readValue(
                file,
                mapper.getTypeFactory().constructCollectionType(List.class, modelClass)
            );

            Object[][] result = new Object[dataList.size()][1];
            for (int i = 0; i < dataList.size(); i++) {
                result[i][0] = dataList.get(i);
            }
            return result;

        } catch (Exception e) {
            throw new RuntimeException("Lỗi đọc file: " + fileName + " — " + e.getMessage());
        }
    }
}
