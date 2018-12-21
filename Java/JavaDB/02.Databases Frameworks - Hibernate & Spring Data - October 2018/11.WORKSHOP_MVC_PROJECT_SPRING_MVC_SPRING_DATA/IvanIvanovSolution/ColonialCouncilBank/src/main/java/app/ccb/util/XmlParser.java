package app.ccb.util;

public interface XmlParser {

   <T> String serialize(T t);

   <T> T deserialize(String src, Class<T> clazz);
}
