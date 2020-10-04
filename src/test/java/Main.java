import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        InputStream file = null;
        try {
            file = new FileInputStream(new File("src/main/resources/config.yaml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Yaml yaml = new Yaml();
        Map<String, Object> result = (Map<String, Object>) yaml.load(file);
        System.out.println(result.toString());
    }
}
