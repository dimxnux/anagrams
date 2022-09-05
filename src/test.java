import java.util.Comparator;
import java.util.List;

public class test {

    public static void main(String[] args) {
//        Comparator a1;
//        Comparable a2;
//
//        Comparable<A> d;
//
//        Comparable<String> tt = new Comparable<String>() {
//            String d;
//
//            @Override
//            public int compareTo(String o) {
//                return d.compareTo(o);
//            }
//        };

        MyEnum enumVar = MyEnum.INSTANCE;
        Comparator<Comparable<Object>> comp = MyEnum.INSTANCE;
        List d;

    }

    static class A implements Comparable<A> {

        @Override
        public int compareTo(A o) {
            return 0;
        }
    }

    enum MyEnum implements Comparator<Comparable<Object>> {
        INSTANCE;

        @Override
        public int compare(Comparable<Object> o1, Comparable<Object> o2) {
            return o1.compareTo(o2);
        }

    }

}
