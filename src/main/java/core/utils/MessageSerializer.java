package core.utils;

import core.models.Message;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class MessageSerializer {
    public static Message DeserializeMessageFromFile(Path filePath) throws IllegalArgumentException {
        String json;
        try {
            var stream = Files.newInputStream(filePath);
            json = readFromInputStream(stream);
            var message = Message.DeserializeFromJsonString(json);
            if (!DeserializeMessageIsValid(message)) {
                throw new IllegalArgumentException("Некорректный формат сообщения");
            }
            return message;
        } catch (IOException exc) {
            throw new IllegalArgumentException(exc.getMessage());
        }
    }

    public static boolean DeserializeMessageIsValid(Message mes) {
        return mes.getKey() != null && mes.getTopic() != null && mes.getValue() != null;
    }

    public static void SerializeMessageFromFile(Message message, Path filePath) throws IOException {
       Files.write(filePath, message.Serialize().getBytes());
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        var resultStringBuilder = new StringBuilder();
        try (var br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
