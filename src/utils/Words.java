package utils;

import utils.sort.BubbleSort;

public class Words {

    public static String sort(String word) {
        char[] chars = word.toCharArray();
        BubbleSort.sortArray(chars);

        return String.valueOf(chars);
    }

}
