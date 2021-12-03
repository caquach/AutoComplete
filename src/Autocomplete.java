import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

// A data type that provides autocomplete functionality for a given set of
// string and weights, using Term and BinarySearchDeluxe.
public class Autocomplete {
    private Term[] terms;

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new NullPointerException("Null Argument: " +
                    "Data Structure does not exist.");
        }
        this.terms = terms;
    }

    // All terms that start with the given prefix, in descending order of
    // weight.
    public Term[] allMatches(String prefix) {
        Term[] prefixSortedTerms = terms;
        Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        Arrays.sort(prefixSortedTerms, prefixOrder);

        int first = BinarySearchDeluxe.firstIndexOf(prefixSortedTerms, term, prefixOrder);
        int last = BinarySearchDeluxe.lastIndexOf(prefixSortedTerms, term, prefixOrder);

        Term[] reverseWeightSorted = new Term[last - first + 1];

        int i = 0;
        while (i < reverseWeightSorted.length) {
            reverseWeightSorted[i] = prefixSortedTerms[first + i];
            i++;
        }

        Arrays.sort(reverseWeightSorted, Term.byReverseWeightOrder());

        return reverseWeightSorted;
    }

    // The number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        Term[] prefixSortedTerms = terms;
        Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        Arrays.sort(prefixSortedTerms, prefixOrder);

        int first = BinarySearchDeluxe.firstIndexOf(prefixSortedTerms, term, prefixOrder);
        int last = BinarySearchDeluxe.lastIndexOf(prefixSortedTerms, term, prefixOrder);

        return last - first + 1;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println(results[i]);
            }
        }
    }
}
