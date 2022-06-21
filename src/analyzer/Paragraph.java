package analyzer;

public class Paragraph {

    private String paragraph;

    public Paragraph(String p) {
        paragraph = p;
    }

    public int numSentences() {
        String[] a = paragraph.split("[!.?]");
        return a.length;
    }

    public int numChars() {
        return paragraph.length();
    }

    public int numWords() {
        String[] words = paragraph.split(" ");
        return words.length;
    }

}
