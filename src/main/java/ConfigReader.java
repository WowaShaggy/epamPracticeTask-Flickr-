import java.io.FileReader;
import java.util.Properties;

public class ConfigReader {
    ConfigReader(){}

    public String Data(String key){

        try(FileReader reader = new FileReader("Config")){
            Properties properties = new Properties();
            properties.load(reader);
            return properties.getProperty(key);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Me extrañaste?";
    }
}

