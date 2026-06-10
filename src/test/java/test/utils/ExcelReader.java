package test.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    private static final String BASE_PATH = "src/test/resources/testdata/";

    // Trả về list các row, mỗi row là Map<columnName, value>
    public static List<Map<String, String>> readSheet(String fileName, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(BASE_PATH + fileName);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) throw new RuntimeException("Sheet không tồn tại: " + sheetName);

            Row header = sheet.getRow(0);
            int colCount = header.getLastCellNum();

            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                Map<String, String> rowData = new LinkedHashMap<>();
                for (int c = 0; c < colCount; c++) {
                    String key   = getCellValue(header.getCell(c));
                    String value = getCellValue(row.getCell(c));
                    rowData.put(key, value);
                }
                data.add(rowData);
            }

        } catch (IOException e) {
            throw new RuntimeException("Không đọc được file: " + fileName, e);
        }

        return data;
    }

    // Trả về Object[][] cho @DataProvider của TestNG
    public static Object[][] toDataProvider(String fileName, String sheetName) {
        List<Map<String, String>> rows = readSheet(fileName, sheetName);
        Object[][] result = new Object[rows.size()][1];
        for (int i = 0; i < rows.size(); i++) {
            result[i][0] = rows.get(i);
        }
        return result;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING  -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default      -> "";
        };
    }
}
