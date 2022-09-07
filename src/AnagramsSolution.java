import utils.Words;
import utils.sort.InsertionSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class AnagramsSolution {

    private static final String WORDS_FILE = "sample.txt";

    private static Map<String, List<String>> readWords() throws IOException {
        Map<String, List<String>> wordsByAnagram = new HashMap<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(WORDS_FILE))) {

            String word;
            while ((word = reader.readLine()) != null) {
                addWordToAnagrams(wordsByAnagram, word);
            }
        }

        return wordsByAnagram;
    }

    private static void addWordToAnagrams(Map<String, List<String>> wordsByAnagram, String word) {
        if (word == null || word.isEmpty() || word.isBlank()) {
            return;
        }

        word = word.trim().toLowerCase();
        String sortedWord = Words.sort(word);

        wordsByAnagram.putIfAbsent(sortedWord, new ArrayList<>());
        wordsByAnagram.get(sortedWord).add(word);
    }

    public static void solve() throws IOException {
        Map<String, List<String>> filteredAnagrams = new TreeMap<>(readWords());
        Iterator<Entry<String, List<String>>> it =
                filteredAnagrams.entrySet().iterator();

        while (it.hasNext()) {
            Entry<String, List<String>> entry = it.next();
            if (entry.getValue().size() <= 1) {
                it.remove();
            }
        }

        List<List<String>> sortedAnagramsByLength =
                new ArrayList<>(filteredAnagrams.values());

        MergeSort.sort(sortedAnagramsByLength);

        for (List<String> list : filteredAnagrams.values()) {
            InsertionSort.sort(list, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareTo(s2);
                }
            });

            for (String anagram : list) {
                System.out.print(anagram + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
    }

}
