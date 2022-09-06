import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordGenerator {

    public static void generate(String outFilename, int wordCount, int maxWordLength)
            throws IOException {
        if (maxWordLength < 1) {
            return;
        }

        // the inclusive range of characters [A-Za-z]
        List<Character> characters = new ArrayList<>();
        for (char i = 'A'; i < 'Z'; ++i) {
            characters.add(i);
        }
        for (char i = 'a'; i < 'z'; ++i) {
            characters.add(i);
        }

        // total number of elements from the range
        int count = characters.size();
        Random r = new Random();

        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(outFilename))) {
            for (int i = 0; i < wordCount; ++i) {
                int wordLength = r.nextInt(maxWordLength) + 1;
                char[] chars = new char[wordLength];

                for (int j = 0; j < wordLength; ++j) {
                    // random character index
                    int offset = r.nextInt(count);
                    chars[j] = characters.get(offset);
                }

                writer.write(chars);
                writer.write('\n');
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // approx 1GB file size
        generate("70mil.txt", 70_000_000, 30);
    }

}
