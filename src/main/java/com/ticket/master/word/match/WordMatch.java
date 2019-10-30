package com.ticket.master.word.match;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordMatch {

    private final static Logger LOGGER = Logger.getLogger(WordMatch.class.getName());

    private final static String WORDS_REGEX = "[^A-Za-z]+";

    public boolean wordsMatch(String ransomNoteFile, String articleFile) {

        //Ransom Note reading using stream
        //Article reading using BufferedReader because , we are not reading whole file if words matched

        //Stream and  BufferedReader automatically closed
        try (Stream<String> ransomNoteStream = Files.lines(Paths.get(getClass().getClassLoader().getResource(ransomNoteFile).toURI()));
             BufferedReader articleBufferedReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(articleFile)))) {

            //Unique words from ransomNote
            //Duplicate words removed
            //Word converted into Lower Case
            CopyOnWriteArraySet<String> ransomNoteWords = ransomNoteStream.map(line -> line.split(WORDS_REGEX))
                    .flatMap(Arrays::stream)
                    .map(s -> s.toLowerCase())
                    .collect(Collectors.toCollection(CopyOnWriteArraySet::new));

            //We read Article file  line By line using BufferedReader
            // Each line we checked All words from Ransom note, if word matched we removed word from Ransom Note words Collection
            //If Ransom words Collection empty, all words matched, we are not reading whole Article file and break while loop

            String articleLine;
            String[] articleWords = null;
            while ((articleLine = articleBufferedReader.readLine()) != null) {
                //Split line for getting words
                articleWords = articleLine.split(WORDS_REGEX);
                //All words of Article line to Matched to Ransom Note Word
                for (String articleWord : articleWords) {
                    //Check word in Ransom note
                    if (ransomNoteWords.contains(articleWord.toLowerCase())) {
                        //Word in Ransom note and removed
                        ransomNoteWords.remove(articleWord.toLowerCase());
                        //if All words in Ransom Note  matched , it empty because all words removed return true
                        if (ransomNoteWords.isEmpty()) {
                            LOGGER.info("All words in Ransom Note  matched with "+articleFile);
                            return true;
                        }
                    }

                }


            }

            LOGGER.info("Words not  matched with Article "+articleFile+" " + ransomNoteWords.toString());

        } catch (IOException | URISyntaxException e) {
            LOGGER.log(Level.SEVERE, "Words in Ransom note not matched with Article "+articleFile+" because we got exception", e);
        }

        //All words in Ransom Note not matched and return false
        return false;
    }


}
