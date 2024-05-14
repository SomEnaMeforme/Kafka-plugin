package core.utils;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;

public class Utils {
    public static class PropertiesLoader {

        public static Properties GetProperties() {
            Properties properties = new Properties();
            var inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream("connection.properties");
            try {
                properties.load(inputStream);
            }
            catch (IOException exp) {
                System.out.println("Exception: " + exp);
            }
            finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return properties;
        }


    }
}
