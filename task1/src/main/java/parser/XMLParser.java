package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Tree;

import javax.xml.bind.*;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLParser implements Parser<Tree> {
    static XMLParser parser;

    public static XMLParser getInstance() {
        return parser == null ? parser = new XMLParser() : parser;
    }

    private XMLParser() {
    }

    @Override
    public Tree read(String string) {
        return XmlToObject(string);
    }

    @Override
    public String write(Tree tree) {
        return jaxbObjectToXML(tree);
    }

    private static Tree XmlToObject(String string) {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Tree.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Tree) jaxbUnmarshaller.unmarshal(new StringReader(string));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    private static String jaxbObjectToXML(Tree tree) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Tree.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(tree, sw);
            return sw.toString();
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
