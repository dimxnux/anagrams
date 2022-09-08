package utils.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort {

    private static <T> void merge(List<T> list, List<T> left,
                              List<T> right, Comparator<T> cmp) {
        int l = 0;
        int r = 0;
        int i = 0;

        while (l < left.size() && r < right.size()) {
            if (cmp.compare(left.get(l), right.get(r)) < 0) {
                list.set(i++, left.get(l++));
            } else {
                list.set(i++, right.get(r++));
            }
        }

        while (l < left.size()) {
            list.set(i++, left.get(l++));
        }
        while (r < right.size()) {
            list.set(i++, right.get(r++));
        }
    }

    public static <T> void sort(List<T> list, Comparator<T> cmp) {
        if (list.size() <= 1) {
            return;
        }

        int mid = list.size() / 2;

        // contains elements from: 0 to mid - 1
        List<T> leftSubList = new ArrayList<>();
        for (int i = 0; i < mid; ++i) {
            leftSubList.add(list.get(i));
        }

        // contains elements from: mid to list.size() - 1
        List<T> rightSubList = new ArrayList<>();
        for (int i = mid; i < list.size(); ++i) {
            rightSubList.add(list.get(i));
        }

        sort(leftSubList, cmp);
        sort(rightSubList, cmp);
        merge(list, leftSubList, rightSubList, cmp);
    }

}
