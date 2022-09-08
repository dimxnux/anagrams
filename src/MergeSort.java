import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort {

    private static <T> void merge(List<T> arr, List<T> left,
                              List<T> right, Comparator<T> cmp) {
        int l = 0;
        int r = 0;
        int i = 0;

        while (l < left.size() && r < right.size()) {
            if (cmp.compare(left.get(l), right.get(r)) < 0) {
                arr.set(i++, left.get(l++));
            } else {
                arr.set(i++, right.get(r++));
            }
        }

        while (l < left.size()) {
            arr.set(i++, left.get(l++));
        }
        while (r < right.size()) {
            arr.set(i++, right.get(r++));
        }
    }

    public static <T> void sort(List<T> arr, Comparator<T> cmp) {
        if (arr.size() <= 1) {
            return;
        }

        int mid = arr.size() / 2;

        // contains elements from: 0 to mid - 1
        List<T> leftSubArr = new ArrayList<>();
        for (int i = 0; i < mid; ++i) {
            leftSubArr.add(arr.get(i));
        }

        // contains elements from: mid to arr.length - 1
        List<T> rightSubArr = new ArrayList<>();
        for (int i = mid; i < arr.size(); ++i) {
            rightSubArr.add(arr.get(i));
        }

        sort(leftSubArr, cmp);
        sort(rightSubArr, cmp);
        merge(arr, leftSubArr, rightSubArr, cmp);
    }

}
