package core.models;

import com.google.gson.Gson;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import java.util.*;

public class Message {
    private final String Topic;
    private HashMap<String, String> Headers;
    private final String Key;
    private final String Value;

    public static Message DeserializeFromJsonString(String json) {
        return new Gson().fromJson(json, Message.class);
    }

    public Message(@NotNull  String topic, @NotNull String key, @NotNull String value) {
        Topic = topic;
        Key = key;
        Value = value;
        Headers = new HashMap<>();
    }

    public Message(@NotNull String topic,
                   @NotNull String key,
                   @NotNull String value,
                   @NotNull HashMap<String, String> headers) {
        this(topic, key, value);
        this.setHeaders(headers);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) return false;
        var other = ((Message) obj);
        return other.Key.equals(Key) && other.Topic.equals(Topic)
                && other.Value.equals(Value) && other.Headers.equals(Headers);
    }

    public String getTopic() {
        return Topic;
    }

    public String getKey() {
        return Key;
    }

    public String getValue() {
        return Value;
    }

    public Map<String, String> getHeaders() {
        return Headers;
    }

    public String Serialize() {
        return new Gson().toJson(this);
    }

    public void AddOrUpdateHeader(String header, String value) {
        if (!Headers.containsKey(header)) {
            Headers.put(header, value);
        } else {
            Headers.replace(header, value);
        }
    }

    private void setHeaders(HashMap<String, String> headers) {
        Headers = headers;
    }
}