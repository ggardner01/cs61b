/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 *
 **/
import java.util.Arrays;
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted;
    }

    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            min = min < i ? min : i;
        }
        int[] countsp = new int[max + 1];
        int[] countsn = new int[-min+1];

        for (int i : arr)
        {
            if(i >= 0) {
                countsp[i]++;
            }else{
                countsn[-i]++;
            }
        }

        int[] sorted = new int[arr.length];
        int k = 0;
        for(int i = min; i < 0; i++){
            for(int j = 0; j < countsn[-i]; j++){
                sorted[k] = i; k++;
            }
        }
        for(int i = 0; i < countsp.length; i++){
            for(int j = 0; j < countsp[i]; j++){
                sorted[k] = i; k++;
            }
        }


        return sorted;
    }
    public static void main(String[] args) {
        int[] someNegative = {9, 5, -4, 2, 1, -2, 5, 3, 0, -2, 3, 1, 1};

        int[] nonNegative = {9, 5, 2, 1, 5, 3, 0, 3, 1, 1};

        int[] sortedSomeNegative = CountingSort.betterCountingSort(someNegative);
        int[] sortedNonNegative = CountingSort.betterCountingSort(nonNegative);
        System.out.println(Arrays.toString(sortedSomeNegative));
        System.out.println(sortedNonNegative);
    }

}