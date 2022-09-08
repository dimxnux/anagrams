import utils.Words;
import utils.sort.MergeSort;

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

        List<List<String>> sortedAnagrams =
                new ArrayList<>(filteredAnagrams.values());

        // compares two List<String> by the length of the first word from the both lists
        Comparator<List<String>> wordLengthComparator =
                new Comparator<List<String>>() {
                    @Override
                    public int compare(List<String> list1, List<String> list2) {
                        int word1Length = list1.get(0).length();
                        int word2Length = list2.get(0).length();

                        return word1Length - word2Length;
                    }
                };

        MergeSort.sort(sortedAnagrams, wordLengthComparator);

        for (List<String> list : filteredAnagrams.values()) {
            // sort the list in natural order
            MergeSort.sort(list, new Comparator<>() {
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
