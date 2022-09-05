import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class AnagramsSolution {

    public static final String WORDS_FILE = "sample.txt";

    public static Map<String, List<String>> readWords() throws IOException {
        Map<String, List<String>> wordsByAnagram = new HashMap<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(WORDS_FILE))) {
            reader.lines()
                    .forEach(word -> addWordToAnagrams(wordsByAnagram, word));
        }

        return wordsByAnagram;
    }

    public static void addWordToAnagrams(Map<String, List<String>> wordsByAnagram, String word) {
        if (word == null || word.isEmpty() || word.isBlank()) {
            return;
        }

        word = word.trim().toLowerCase();
        String sortedWord = sortWord(word);

        wordsByAnagram.putIfAbsent(sortedWord, new ArrayList<>());
        wordsByAnagram.get(sortedWord).add(word);
    }

    private static String sortWord(String word) {
        char[] chars = word.toCharArray();
        int n = chars.length;

        for (int i = 0; i < n - 1; ++i) {
            for (int j = 0; j < n - 1 - i; ++j) {
                if (chars[j] > chars[j + 1]) {
                    char temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }

        return String.valueOf(chars);
    }

    public static void solve() throws IOException {
        Map<String, List<String>> wordsByAnagram = readWords();

        wordsByAnagram.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .sorted(Map.Entry.comparingByKey())
                .flatMap(entry -> Stream.of(entry.getValue()))
                .forEach(list -> {
                    list.sort(Comparator.naturalOrder());
                    list.forEach(word -> System.out.print(word + " "));
                    System.out.println();
                });
    }

    public static void main(String[] args) throws IOException {
        solve();
    }

}
