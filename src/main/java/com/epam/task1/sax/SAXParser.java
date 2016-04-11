package com.epam.task1.sax;

import com.epam.task1.otherclasses.Speech;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by Oleg on 09.04.2016.
 */
public class SAXParser {

    public static List<Speech> performParse(String url) throws SAXException, IOException {

        XMLReader reader = XMLReaderFactory.createXMLReader();
        reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        SpeechSAXHandler handler = new SpeechSAXHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource(url));

        return handler.getSpeechList();
    }

}
