package com.epam.task1.dom;

import com.epam.task1.otherclasses.Speech;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oleg on 09.04.2016.
 */
public class MyDOMParser {

    public static List<Speech> performParse(String url) throws IOException, SAXException {

        DOMParser parser = new DOMParser();
        parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        parser.parse(url);
        Document document = parser.getDocument();
        Element root = document.getDocumentElement();
        List<Speech> menu = new LinkedList<>();

        NodeList speechNodes = root.getElementsByTagName("SPEECH");
        Speech speech = null;
        for (int i = 0; i < speechNodes.getLength(); i++) {
            speech = new Speech();
            Element speechElement = (Element) speechNodes.item(i);
            speech.setSpeaker(getSingleChild(speechElement, "SPEAKER").getTextContent().trim());

            NodeList lineNodes = speechElement.getChildNodes();

            for (int j = 0; j < lineNodes.getLength(); j++) {
                if (lineNodes.item(j).getNodeName().equals("LINE")) {
                    speech.addLine(lineNodes.item(j).getTextContent().trim());
                }
            }
            menu.add(speech);
        }
        return menu;
    }

    private static Element getSingleChild(Element element, String childName) {
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }
}

