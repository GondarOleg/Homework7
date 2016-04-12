package com.epam.task1;

import com.epam.task1.otherclasses.Pair;
import com.epam.task1.otherclasses.Speech;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by O.Gondar on 11.04.2016.
 */
public class View {

    public static final String DEFAULT_PIECE_URL_FOR_PARSE = "http://www.ibiblio.org/xml/examples/shakespeare/all_well.xml";

    static final Logger logger = Logger.getLogger(View.class);

    private View() {
    }

    public static void main(String[] args) throws SAXException {

        org.apache.log4j.BasicConfigurator.configure();

        String url = getPieceChoose();
        String parser = getParserChoose();

        List<Speech> speechList;
        speechList = Controller.performParse(parser, url);
        for (Map.Entry<String, Pair> m :
                Controller.countStatistics(speechList).entrySet()) {
            System.out.println(m.getKey() + ". Speeches: " + m.getValue().getSpeechCount()
                                          + " Average words: " + m.getValue().getWordsCount() / m.getValue().getSpeechCount());
        }
    }

    public static String getPieceChoose() throws SAXException {
        Document doc;
        try {
            doc = Jsoup.connect("http://www.ibiblio.org/xml/examples/shakespeare/").get();
            String title = doc.title();
            System.out.println(title);
            System.out.println("Enter number for select piece for parse:");

            Elements links = doc.select("LI a[href]");
            for (int i = 0; i < links.size(); i++) {
                Element link = links.get(i);
                System.out.println(i + ": " + link.text());

            }
            System.out.println("Enter number for select piece for parse from list above:");
            Scanner sc = new Scanner(System.in);

            int i = sc.nextInt();
            if (i >= 0 && i < 36) {
                return links.get(i).absUrl("href");
            }
        } catch (IOException e) {
            logger.error(e);
        }
        return DEFAULT_PIECE_URL_FOR_PARSE;
    }

    public static String getParserChoose() {
        System.out.println("Enter parsing method (DOM, SAX or StAX (by default)):");
        return new Scanner(System.in).nextLine();
    }
}

