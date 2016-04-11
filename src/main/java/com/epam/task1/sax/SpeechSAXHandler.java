package com.epam.task1.sax;

import com.epam.task1.otherclasses.Speech;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oleg on 09.04.2016.
 */
public class SpeechSAXHandler extends DefaultHandler {

    private List<Speech> speechList = new LinkedList<>();
    private Speech speech;
    private StringBuilder text;

    public List<Speech> getSpeechList() {
        return speechList;
    }

    public void startDocument() throws SAXException {

        System.out.println("Parsing started.");
    }

    public void endDocument() throws SAXException {

        System.out.println("Parsing ended.");
    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if (qName.equals("SPEECH")) {
            speech = new Speech();
        }
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        switch (qName.toUpperCase()) {
            case "SPEAKER":
                speech.setSpeaker(text.toString());
                break;
            case "LINE":
                speech.addLine(text.toString());
                break;
            case "SPEECH":
                speechList.add(speech);
                speech = null;
                break;
        }
    }


}
