package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;

public class SuggestionEngineTest {

    private final SuggestionEngine suggestionEngine = new SuggestionEngine();

    @Test
    public void testGenerateSuggestions() throws Exception {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("hellw").contains("hello"));
    }

    @Test
    public void testGenerateSuggestionsFail() throws Exception {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        Assertions.assertFalse(suggestionEngine.generateSuggestions("hello").contains("hello"));
    }

    @Test
    public void testEmptyDictionary() throws Exception {
        // Test when the dictionary is empty
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("empty.txt").getPath()));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("xyz").isEmpty());
    }

    @Test
    public void testGenerateSuggestionsWithNumbers() throws Exception {
        // Test when the input contains numbers
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("h3ll0").contains("hello"));
    }

    @Test
    public void testGenerateSuggestionsWithSpecialCharacters() throws Exception {
        // Test when the input contains special characters
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("w@rld").contains("world"));
    }
}
