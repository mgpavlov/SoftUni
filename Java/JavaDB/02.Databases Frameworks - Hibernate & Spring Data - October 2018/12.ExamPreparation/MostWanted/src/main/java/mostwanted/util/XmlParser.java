package mostwanted.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface XmlParser {

    <O> O parseXml(Class<O> objectClass, String filePath) throws JAXBException, FileNotFoundException, UnsupportedEncodingException;
    <T> void exportToXml(T object, String filePath) throws JAXBException;
}
