package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CompEngine {

    private final String text;
    private final List<Paragraph> paragraphs = new ArrayList<>();
    private final List<Sentence> sentences = new ArrayList<>();

    public CompEngine(String text) {
        this.text = text;
        sortInput();
    }

    public void sortInput() {
        String[] s = text.split("[!.?]");
        String[] p = text.split("\n");

        for (String value : s) {
            sentences.add(new Sentence(value));
        }

        for (String value : p) {
            paragraphs.add(new Paragraph(value));
        }
    }

    public double wordsPerSentence() {
        double wps = 0;
        for (Sentence sentence : sentences) {
            wps = wps + sentence.numWords();
        }
        return wps / sentences.size();
    }

    public double charsPerSentence() {
        double cps = 0;
        for (Sentence sentence : sentences) {
            cps = cps + sentence.getChars();
        }
        return cps / sentences.size();
    }

    public double wordsPerParagraph() {
        double wps = 0;
        for (Paragraph paragraph : paragraphs) {
            wps = wps + paragraph.numWords();
        }
        return wps / paragraphs.size();
    }

    public double charsPerParagraph() {
        double cps = 0;
        for (Paragraph paragraph : paragraphs) {
            cps = cps + paragraph.numChars();
        }
        return cps / paragraphs.size();
    }

    public double sentencesPerParagraph() {
        double cps = 0;
        for (Paragraph paragraph : paragraphs) {
            cps = cps + paragraph.numSentences();
        }
        return cps / paragraphs.size();
    }

    public Map<String, Integer> mostCommonWords() throws IOException {
        Map<String, Integer> words = new LinkedHashMap<>();
        ArrayList<String> w = new ArrayList<>();
        List<String> conjunctions = Arrays.asList(Files.readString(Paths.get("src/conjunctions.txt")).replaceAll("\r", "").split("\n"));

        for (Sentence sentence : sentences) {
            String[] sw = sentence.getWords();

            for (String s : sw) {
                if (!conjunctions.contains(s) && !s.equalsIgnoreCase("")) {
                    w.add(s);
                }
            }
        }

        for (String s : w) {
            if (words.containsKey(s)) {
                words.put(s, words.get(s) + 1);
            } else {
                words.put(s, 1);
            }
        }

        return words.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

}
