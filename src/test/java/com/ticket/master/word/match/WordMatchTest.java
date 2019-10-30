package com.ticket.master.word.match;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WordMatchTest {

    private WordMatch wordMatch;

    @Before
    public void setUp() {
        wordMatch = new WordMatch();
    }

    @Test
    public void wordsMatch_whenRansomNoteWordsNotMatchedWithDeadMen() {
        boolean isWordMatched= wordMatch.wordsMatch("ransom_note_not_matched.txt", "gutenberg_e_book_dead_men.txt");
        assertFalse(isWordMatched);
    }

    @Test
    public void wordsMatch_whenRansomNoteWordsNotMatchedWithFriends() {
        boolean isWordMatched= wordMatch.wordsMatch("ransom_note_not_matched.txt", "gutenberg_e_book_friends.txt");
        assertFalse(isWordMatched);
    }
    @Test
    public void wordsMatch_whenRansomNoteWordsMatchedWithDeadMen() {
        boolean isWordMatched= wordMatch.wordsMatch("ransom_note_matched_dead_men.txt", "gutenberg_e_book_dead_men.txt");
        assertTrue(isWordMatched);
    }




    @Test
    public void wordsMatch_whenRansomNoteWordsMatchedWithFriends() {
        boolean isWordMatched= wordMatch.wordsMatch("ransom_note_matched_friends.txt", "gutenberg_e_book_friends.txt");
        assertTrue(isWordMatched);
    }

    @Test
    public void wordsMatch_whenRansomNoteWordsMatchedWithPlayEquipment() {
        boolean isWordMatched= wordMatch.wordsMatch("ransom_note_play_equipment.txt", "gutenberg_play_equipment.txt");
        assertTrue(isWordMatched);
    }


}