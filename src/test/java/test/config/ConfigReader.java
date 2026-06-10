package test.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        // 1. Load default (device settings, appium URL, timing)
        load("config/default.properties");

        // 2. Load profile — truyền qua: mvn test -Dprofile=sit-dev-1
        //    Nếu không truyền thì dùng sit-dev-1 làm mặc định
        String profile = System.getProperty("profile", "sit-dev-1");
        load("config/" + profile + ".properties");
    }

    private static void load(String path) {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) throw new RuntimeException("Không tìm thấy: " + path);
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi đọc " + path + ": " + e.getMessage());
        }
    }

    public static String get(String key) {
        // System property luôn được ưu tiên cao nhất
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
