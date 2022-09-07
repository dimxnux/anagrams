import utils.Words;

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


    private static <T extends Comparable<T>> void sortList(List<T> list) {
        // insertion sort
        int n = list.size();
        for (int i = 0; i < n - 1; ++i) {
            int j = i;
            T key = list.get(i + 1);

            while (j >= 0
                    && (list.get(j).compareTo(key) > 0)) {
                list.set(j + 1, list.get(j));
                --j;
            }

            list.set(j + 1, key);
        }
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

        for (List<String> list : filteredAnagrams.values()) {
            sortList(list);

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
