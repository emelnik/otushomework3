package conf_readers;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader implements IConfReader{


    @Override
    public Map<String, String> read() {

        String rootDir = System.getProperty("user.dir");

        Map<String,String> propMap = new HashMap<>();

        try (InputStream input = new FileInputStream(
                String.format("%s/src/resources/%s", rootDir, "db.properties"))){

            Properties properties = new Properties();

            properties.load(input);

            for (Object key: properties.keySet()){
                propMap.put((String) key, (String) properties.get(key));
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return propMap;

    }
}
