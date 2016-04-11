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
    public void testValidationXML(){
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME_FOR_TESTS);
            fileWriter.write("test");
            fileWriter.close();
            Assert.assertEquals("instance document is good", ValidatePOM.validate("pom.xml"));
            Assert.assertEquals("instance document is invalid!", ValidatePOM.validate(FILE_NAME_FOR_TESTS));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWritingWithDOM(){
        try {
            WritingWithDOM.performWrite(FILE_NAME_FOR_TESTS);
            Assert.assertTrue("instance document is good".equals(ValidatePOM.validate(FILE_NAME_FOR_TESTS)));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWritingWithStAX(){
        try {
            WritingWithStAX.performWrite(FILE_NAME_FOR_TESTS);
            try {
                Assert.assertTrue("instance document is good".equals(ValidatePOM.validate(FILE_NAME_FOR_TESTS)));
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }
}
