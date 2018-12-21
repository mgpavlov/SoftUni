package app.ccb.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class XmlParserImpl implements XmlParser {


    @Override
    public <T> String serialize(T t) {
        try (StringWriter writer = new StringWriter()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(t, writer);
            return writer.toString();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T deserialize(String src, Class<T> clazz) {
        try (StringReader reader = new StringReader(src)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return clazz.cast(unmarshaller.unmarshal(reader));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
