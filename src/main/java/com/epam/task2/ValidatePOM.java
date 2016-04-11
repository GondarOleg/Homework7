package com.epam.task2;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Oleg on 11.04.2016.
 */
public class ValidatePOM {

    public static String validate(String fileName) throws ParserConfigurationException, IOException, SAXException {

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(new URL("https://maven.apache.org/xsd/maven-4.0.0.xsd").openStream()));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(fileName)));
            return "instance document is good";
        } catch (Exception e) {
            return "instance document is invalid!";
        }

    }
}
