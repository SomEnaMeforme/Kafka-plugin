package core.utils;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class Utils {
    public static class PropertiesLoader {

        public static Properties GetProperties() {
            //TODO: в будущем должен получать путь к конфигу и сериализовать его в настройки
            String kafkaServer = "127.0.0.1:29092";
            Properties properties = new Properties();
            properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
            properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            return properties;
        }


    }
}
