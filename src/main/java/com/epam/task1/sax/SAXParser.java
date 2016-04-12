package com.epam.task1.sax;

import com.epam.task1.otherclasses.Speech;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Oleg on 09.04.2016.
 */
public class SAXParser {

    static final Logger logger = Logger.getLogger(SAXParser.class);

    private SAXParser() {
    }

    public static List<Speech> performParse(String url) {

        XMLReader reader = null;
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            SpeechSAXHandler handler = new SpeechSAXHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(url));

            return handler.getSpeechList();
        } catch (SAXException e) {
            logger.error("Sorry, in SAX something wrong!", e);
        } catch (IOException e) {
            logger.error("Sorry, in IO something wrong!", e);
        }
        return new LinkedList<>();
    }


}
