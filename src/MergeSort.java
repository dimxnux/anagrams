import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort {

    private static Comparator<List<String>> cmp = new Comparator<>() {
        @Override
        public int compare(List<String> l1, List<String> l2) {
            return l1.get(0).length() - l2.get(0).length();
        }
    };

    private static void merge(List<List<String>> arr, List<List<String>> left,
                              List<List<String>> right) {
        int l = 0;
        int r = 0;
        int i = 0;

        while (l < left.size() && r < right.size()) {
            if (cmp.compare(left.get(l), right.get(r)) >= 0) {
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

    public static void sort(List<List<String>> arr) {
        if (arr.size() <= 1) {
            return;
        }

        int mid = arr.size() / 2;

        // contains elements from: 0 to mid - 1
        List<List<String>> leftSubArr = new ArrayList<>();
        for (int i = 0; i < mid; ++i) {
            leftSubArr.add(arr.get(i));
        }

        // contains elements from: mid to arr.length - 1
        List<List<String>> rightSubArr = new ArrayList<>();
        for (int i = mid; i < arr.size(); ++i) {
            rightSubArr.add(arr.get(i));
        }

        sort(leftSubArr);
        sort(rightSubArr);
        merge(arr, leftSubArr, rightSubArr);
    }

}
