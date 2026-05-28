package test.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is == null) throw new RuntimeException("Không tìm thấy config.properties");
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi đọc config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        String sysProp = System.getProperty(key);
        if (sysProp != null) return sysProp;

        String value = props.getProperty(key);
        if (value == null) throw new RuntimeException("Key không tồn tại: " + key);
        return value;
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
