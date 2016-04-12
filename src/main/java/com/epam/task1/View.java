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
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by O.Gondar on 11.04.2016.
 */
public class View {

    private static final int PIECES_MIN_NUMBER = 0;
    private static final int PIECES_MAX_NUMBER = 36;
    private static final String PIECES_URL_FOR_PARSE = "http://www.ibiblio.org/xml/examples/shakespeare/";
    private static final String DEFAULT_PIECE_URL_FOR_PARSE = "http://www.ibiblio.org/xml/examples/shakespeare/all_well.xml";
    private static final String ASK_FOR_PIECE_SELECTION = "Enter number to select piece for parse from list above (by default \"Alls Well That Ends Well\"):";
    private static final String ASK_FOR_PARSER_SELECTION = "Enter parsing method (DOM, SAX or StAX (by default)):";

    private static final Logger logger = Logger.getLogger(View.class);

    private View() {
    }

    public static void main(String[] args) throws SAXException {

        org.apache.log4j.BasicConfigurator.configure();

        String url = getPieceChoose();
        String parser = getParserChoose();
        List<Speech> speechList = Controller.performParse(parser, url);

        for (Map.Entry<String, Pair> m :
                Controller.countStatistics(speechList).entrySet()) {
            System.out.println(m.getKey() + ". Speeches: " + m.getValue().getSpeechCount()
                    + " Average words in speech: " + m.getValue().getWordsCount() / m.getValue().getSpeechCount());
        }
    }

    public static String getPieceChoose() throws SAXException {

        Scanner sc = new Scanner(System.in);
        Elements links = getAndShowLinksFromSite();
        System.out.println(ASK_FOR_PIECE_SELECTION);
        try {
            int userChoise = sc.nextInt();
            if (userChoise >= PIECES_MIN_NUMBER && userChoise < PIECES_MAX_NUMBER) {
                return links.get(userChoise).absUrl("href");
            }
        } catch (InputMismatchException e) {
            org.apache.log4j.BasicConfigurator.configure();
            logger.error("Wrong input, continue with default value - " + DEFAULT_PIECE_URL_FOR_PARSE);
        }
        return DEFAULT_PIECE_URL_FOR_PARSE;
    }

    public static Elements getAndShowLinksFromSite() {
        Document doc;
        try {
            doc = Jsoup.connect(PIECES_URL_FOR_PARSE).get();
            String title = doc.title();
            System.out.println(title);
            Elements links = doc.select("LI a[href]");
            for (int i = 0; i < links.size(); i++) {
                Element link = links.get(i);
                System.out.println(i + ": " + link.text());
            }
            return links;
        } catch (IOException e) {
            org.apache.log4j.BasicConfigurator.configure();
            logger.error(e);
        }
        return new Elements();
    }

    public static String getParserChoose() {
        System.out.println(ASK_FOR_PARSER_SELECTION);
        return new Scanner(System.in).nextLine();
    }
}

