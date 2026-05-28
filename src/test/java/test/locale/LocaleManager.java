package test.locale;

public class LocaleManager {

    public enum Env {
        ANDROID_EN, ANDROID_VN, IOS_EN, IOS_VN
    }

    private static Env currentEnv;

    public static void init(String env) {
        currentEnv = switch (env) {
            case "androidEng" -> Env.ANDROID_EN;
            case "androidVn"  -> Env.ANDROID_VN;
            case "iOSEng"     -> Env.IOS_EN;
            case "iOSVn"      -> Env.IOS_VN;
            default -> throw new RuntimeException("Unknown env: " + env
                    + " — dùng: androidEng / androidVn / iOSEng / iOSVn");
        };
        System.out.println("LocaleManager init: " + currentEnv);
    }

    public static String get(Keyword keyword) {
        if (currentEnv == null) {
            throw new RuntimeException("LocaleManager chưa init — gọi LocaleManager.init() trong @BeforeSuite");
        }
        return switch (currentEnv) {
            case ANDROID_EN -> keyword.androidEng();
            case ANDROID_VN -> keyword.androidVn();
            case IOS_EN     -> keyword.iOSEng();
            case IOS_VN     -> keyword.iOSVn();
        };
    }

    public static Env getCurrentEnv() {
        return currentEnv;
    }

    public static boolean isAndroid() {
        return currentEnv == Env.ANDROID_EN || currentEnv == Env.ANDROID_VN;
    }

    public static boolean isEnglish() {
        return currentEnv == Env.ANDROID_EN || currentEnv == Env.IOS_EN;
    }
}
