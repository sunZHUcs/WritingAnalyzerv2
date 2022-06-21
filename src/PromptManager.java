import analyzer.CompEngine;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PromptManager {

    private static  long start;
    private static  long end;
    public static void begin() throws IOException {

        System.out.println("Welcome to WritingAnalyzer v2 by @ bryt#4751 on Discord");
        System.out.println("To analyze text, input .txt files into the folder named \"input\". When ready, type \"continue\"");

        Scanner scanner = new Scanner(System.in);
        boolean cont = scanner.nextLine().equalsIgnoreCase("continue");
        if (cont) {
            FileParser.parseFiles();
        }
        textSelection();
    }

    public static void textSelection() throws IOException {
        System.out.println("\nDetected Files:");
        for (int i = 0; i < FileParser.files.size(); i++) {
            System.out.println(i + ":        " + FileParser.files.get(i));
        }
        System.out.println("Please select a file to analyze according to it's assigned number. Once input, the program will continue.");
        Scanner scanner = new Scanner(System.in);
        File file = FileParser.getFile(scanner.nextInt());

        System.out.println("Confirm that you want to analyze \"" + file.getName() + "\" with either \"yes\" or \"no\"");
        String conf = new Scanner(System.in).nextLine();

        if (conf.equalsIgnoreCase("yes")) {
            System.out.println("Analyzing text...");
            start = System.currentTimeMillis();
            Main.compEngine = new CompEngine(FileParser.readFile(file));
            results();
        } else {
            textSelection();
        }
    }

    public static void results() throws IOException {
        System.out.println();
        System.out.println("Most Common Words: " + Main.compEngine.mostCommonWords());
        System.out.print("Words Per Paragraph: ");
        System.out.printf("%.2f\n", Main.compEngine.wordsPerParagraph());
        System.out.print("Words Per Sentence: ");
        System.out.printf("%.2f\n", Main.compEngine.wordsPerSentence());
        System.out.print("Characters Per Paragraph: ");
        System.out.printf("%.2f\n", Main.compEngine.charsPerParagraph());
        System.out.print("Characters Per Sentence: ");
        System.out.printf("%.2f\n", Main.compEngine.charsPerSentence());
        System.out.print("Sentences Per Paragraph: ");
        System.out.printf("%.2f\n", Main.compEngine.sentencesPerParagraph());
        end = System.currentTimeMillis();
        System.out.println("Operation Time: " + (end-start) + "ms");
    }
}
