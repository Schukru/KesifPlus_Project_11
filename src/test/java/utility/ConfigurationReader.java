package utility;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    // ConfigurationReader classını .properties dosyasını okumak için oluşturuyoruz.
    public static Properties properties;
    // static yaptık çünkü nesne oluşturmadan doğrudan erişim sağlamak istedik
    static {

        String path = "src/test/resources/configuration.properties";

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            properties = new Properties();
            properties.load(fileInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
        //properties.getProperty("browser");
    }
}