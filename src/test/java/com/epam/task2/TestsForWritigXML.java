package com.epam.task2;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Oleg on 11.04.2016.
 */
public class TestsForWritigXML {

    public static final String FILE_NAME_FOR_TESTS = "pom1.xml";

    @Test
    public void testValidationXML() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME_FOR_TESTS);
            fileWriter.write("test");
            fileWriter.close();
            Assert.assertEquals("instance document is invalid!", ValidatePOM.validate(FILE_NAME_FOR_TESTS));
            Assert.assertEquals("instance document is good", ValidatePOM.validate("pom.xml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWritingWithDOM() {
        WritingWithDOM.performWrite(FILE_NAME_FOR_TESTS);
        Assert.assertTrue("instance document is good".equals(ValidatePOM.validate(FILE_NAME_FOR_TESTS)));
    }

    @Test
    public void testWritingWithStAX() {
        WritingWithStAX.performWrite(FILE_NAME_FOR_TESTS);
        Assert.assertTrue("instance document is good".equals(ValidatePOM.validate(FILE_NAME_FOR_TESTS)));

    }
}
