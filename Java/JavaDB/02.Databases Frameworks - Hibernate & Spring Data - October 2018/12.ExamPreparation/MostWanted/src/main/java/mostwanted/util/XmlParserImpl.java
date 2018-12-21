package mostwanted.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class XmlParserImpl implements XmlParser {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parseXml(Class<T> objectClass, String filePath) throws JAXBException, FileNotFoundException, UnsupportedEncodingException {
        JAXBContext context=JAXBContext.newInstance(objectClass);

        File file=new File(filePath);
        InputStream inputStream= new FileInputStream(file);
        InputStreamReader reader=new InputStreamReader(inputStream );

        Unmarshaller unmarshaller=context.createUnmarshaller();

        T result = (T) unmarshaller.unmarshal(reader);
        return result;
    }

    public <T> void exportToXml(T object, String filePath) throws JAXBException{
        JAXBContext context=JAXBContext.newInstance(object.getClass());

        Marshaller marshaller=context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        marshaller.marshal(object,new File(filePath));

    }
}
