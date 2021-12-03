import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

// Implements binary search for clients that may want to know the index of
// either the first or last key in a (sorted) collection of keys.
public class BinarySearchDeluxe {
    // The index of the first key in a[] that equals the search key,
    // or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key,
                                         Comparator<Key> comparator) {

        if (a == null || key == null || comparator == null) {
            throw new NullPointerException("Null Argument: " +
                    "One of the arguments do not exist.");
        }

        int floor = 0;
        int ceiling = a.length - 1;

        while (floor <= ceiling) {

            int middle = ((floor + (ceiling - 1)) / 2) + 1;
            int compared = comparator.compare(a[middle], key);

            if (compared == 0) {
                for (int i = floor; i <= middle; i++) {
                    if (comparator.compare(a[i], key) == 0) {
                        return i;
                    }
                }
            }

            if (compared <= -1) {
                floor = middle + 1;
            } else {
                ceiling = middle - 1;
            }
        }

        return -1;
    }

    // The index of the last key in a[] that equals the search key,
    // or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key,
                                        Comparator<Key> comparator) {

        if (a == null || key == null || comparator == null) {
            throw new NullPointerException("Null Argument: " +
                    "One of the arguments do not exist.");
        }

        int floor = 0;
        int ceiling = a.length - 1;

        while (floor <= ceiling) {

            int middle = ((floor + (ceiling - 1)) / 2) + 1;
            int compared = comparator.compare(a[middle], key);

            if (compared == 0) {
                for (int i = ceiling; i >= middle; i--) {
                    if (comparator.compare(key, a[i]) == 0) {
                        return i;
                    }
                }
            }

            if (compared <= -1) {
                floor = middle + 1;
            } else {
                ceiling = middle - 1;
            }
        }

        return -1;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        String prefix = args[1];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        Arrays.sort(terms);
        Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        int i = BinarySearchDeluxe.firstIndexOf(terms, term, prefixOrder);
        int j = BinarySearchDeluxe.lastIndexOf(terms, term, prefixOrder);
        int count = i == -1 && j == -1 ? 0 : j - i + 1;
        StdOut.println(count);
    }
}
