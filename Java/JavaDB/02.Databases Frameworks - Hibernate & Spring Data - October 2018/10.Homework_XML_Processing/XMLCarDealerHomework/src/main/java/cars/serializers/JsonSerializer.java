package cars.serializers;

import cars.exceptions.DeserializeException;
import cars.exceptions.SerializeException;
import cars.io.FileParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("json")
public class JsonSerializer implements Serializer {

    private Gson gson;

    private final FileParser fileParser;

    @Autowired
    public JsonSerializer(FileParser fileParser) {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
                .create();
        this.fileParser = fileParser;
    }

    @Override
    public <T> T deserialize(Class<T> tClass, String fileName) {
        try {
            String file = this.fileParser.readFile(fileName);
            return this.gson.fromJson(file, tClass);
        } catch (IOException e) {
            throw new DeserializeException("Cannot deserialize " + fileName + " file", e);
        }
    }

    @Override
    public <T> void serialize(T object, String fileName) {
        String content = this.gson.toJson(object);
        try {
            this.fileParser.writeFile(fileName, content);
        } catch (IOException e) {
            throw new SerializeException("Cannot serialize " + object, e);
        }
    }
}