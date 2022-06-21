package analyzer;

public class Sentence {

    private String sentence;

    public Sentence(String s) {
        sentence = s;
    }

    public int getChars() {
        return sentence.length();
    }

    public int numWords() {
        String[] words = sentence.split(" ");
        return words.length;
    }

    public String[] getWords() {
        sentence = sentence.replaceAll("[,]", "");
        return sentence.split(" ");
    }
}
