package utils.sort;

import java.util.Comparator;
import java.util.List;

public class InsertionSort {

    public static <T> void sort(List<T> list, Comparator<T> comparator) {
        int n = list.size();

        for (int i = 0; i < n - 1; ++i) {
            int j = i;
            T key = list.get(i + 1);

            while (j >= 0
                    && (comparator.compare(list.get(j), key) > 0)) {
                list.set(j + 1, list.get(j));
                --j;
            }

            list.set(j + 1, key);
        }
    }

}
