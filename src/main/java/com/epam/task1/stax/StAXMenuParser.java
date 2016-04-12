package com.epam.task1.stax;

import com.epam.task1.otherclasses.Speech;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oleg on 09.04.2016.
 */
public class StAXMenuParser {

    static final Logger logger = Logger.getLogger(StAXMenuParser.class);

    private StAXMenuParser() {
    }

    public static List<Speech> performParse(String url) throws IOException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        inputFactory.setProperty("http://java.sun.com/xml/stream/properties/ignore-external-dtd", Boolean.TRUE);
        try {
            InputStream input = new URL(url).openStream();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);

            return process(reader);
        } catch (XMLStreamException e) {
            logger.error(e);
        }
        return new LinkedList<>();
    }

    private static List<Speech> process(XMLStreamReader reader) throws XMLStreamException {
        List<Speech> menu = new LinkedList<>();
        Speech speech = null;
        String elementName = null;
        while (reader.hasNext()) {

            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = reader.getLocalName();
                    switch (elementName) {
                        case "SPEECH":
                            speech = new Speech();
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    if (elementName != null) {

                        switch (elementName) {
                            case "SPEAKER":
                                if (speech != null) {
                                    speech.setSpeaker(text);
                                }
                                break;
                            case "LINE":
                                if (speech != null) {
                                    speech.addLine(text);
                                }
                                break;
                            case "SPEECH":
                                break;
                        }
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = reader.getLocalName();
                    switch (elementName) {
                        case "SPEECH":
                            menu.add(speech);
                    }
            }
        }
        return menu;
    }
}
