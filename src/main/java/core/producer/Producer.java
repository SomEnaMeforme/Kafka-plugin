package core.producer;

import com.intellij.ide.plugins.newui.TabHeaderListener;
import core.utils.Utils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class Producer {
    public void SendMessage(String topic, String key, String value) {
        var v = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(null);
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(Utils.PropertiesLoader.GetProperties());
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);
        Thread.currentThread().setContextClassLoader(v);
        producer.send(producerRecord);
    }
}
