package com.epam.task1.otherclasses;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oleg on 09.04.2016.
 */
public class Speech {

    private String speaker;
    private List<String> speech;

    public Speech() {
        this.speech = new LinkedList<String>();
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public List<String> getSpeech() {
        return speech;
    }

    public void addLine(String text){
        speech.add(text);
    }

    public void setSpeech(List<String> speech) {
        this.speech = speech;
    }

    public int getWordsInSpeech(){
        int words = 0;
        for (String string:
             speech) {
            words += string.split("\\s+").length;
        }
        return words;
    }

    public void showAll(){
        System.out.println("Speaker: " + speaker);
        for (String s:
             speech) {
            System.out.println("-" + s);
        }
    }

}
