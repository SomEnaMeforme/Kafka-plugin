import org.junit.Assert;
import org.junit.Test;
import core.models.Message;
import java.util.HashMap;


public class MessageTests {
    @Test
    public void MessageSerializeTest() {
        var mes = new Message("topic", "key", "value");
        var serializeMes = mes.Serialize();
        Assert.assertEquals("{\"Topic\":\"topic\",\"Headers\":{},\"Key\":\"key\",\"Value\":\"value\"}",
                serializeMes);
    }
    @Test
    public void MessageDeserializeTest() {
        var serializeMes = "{\"Topic\":\"topic\",\"Headers\":{},\"Key\":\"key\",\"Value\":\"value\"}";
        var mes = new Message("topic", "key", "value");
        Assert.assertEquals(mes, Message.DeserializeFromJsonString(serializeMes));
    }
    @Test
    public void MessageSerializeWithHeadersTest() {
        var headers = new HashMap<String, String>();
        headers.put("headerKey", "headerValue");
        headers.put("headerKey1", "headerValue1");
        var mes = new Message("topic", "key", "value", headers);
        var serializeMes = mes.Serialize();
        Assert.assertEquals("{\"Topic\":\"topic\",\"Headers\":{\"headerKey1\":\"headerValue1\",\"headerKey\":\"headerValue\"},\"Key\":\"key\",\"Value\":\"value\"}",
                serializeMes);
    }
    @Test
    public void MessageDeserializeWithHeadersTest() {
        var headers = new HashMap<String, String>();
        headers.put("headerKey", "headerValue");
        headers.put("headerKey1", "headerValue1");
        var serializeMes = "{\"Topic\":\"topic\",\"Headers\":{\"headerKey1\":\"headerValue1\",\"headerKey\":\"headerValue\"},\"Key\":\"key\",\"Value\":\"value\"}";
        var mes = new Message("topic", "key", "value", headers);
        Assert.assertEquals(mes, Message.DeserializeFromJsonString(serializeMes));
    }

    @Test
    public void MessageDeserializeWithUnCorrectField() {
        var serializeMes = "{\"Headers\":{},\"Key\":\"key\",\"UNCORRECT\":\"value\"}";
        var mes = Message.DeserializeFromJsonString(serializeMes);
        Assert.assertNull(mes.getValue());
        Assert.assertNull(mes.getTopic());
    }
}