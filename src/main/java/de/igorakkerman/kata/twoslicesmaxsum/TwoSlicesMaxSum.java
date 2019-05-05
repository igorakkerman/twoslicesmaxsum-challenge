package de.igorakkerman.kata.twoslicesmaxsum;

public class TwoSlicesMaxSum {
    public static int twoSlicesMaxSum(int[] a, int k, int l) {
        if (a.length < k + l)
            return -1;

        int[] kSums = new int[a.length];
        int[] lSums = new int[a.length];

        collectSums(a, kSums, k, 0, a.length - (k + l));
        collectSums(a, lSums, l, k, a.length - l);

        return findMaxSum(a, k, l, kSums, lSums);
    }

    private static void collectSums(int[] a, int[] sums, int length, int first, int lastStart) {
        int sum = sliceSum(a, first, length);

        sums[first] = sum;
        for (int start = first + 1; start <= lastStart; start++) {
            sum = sum - a[start - 1] + a[start + length - 1];
            sums[start] = sum;
        }
    }

    private static int findMaxSum(int[] a, int k, int l, int[] kSums, int[] lSums) {
        int max = 0;
        for (int i = 0; i <= a.length - (k + l); i++)
            for (int j = i + k; j <= a.length - l; j++)
                max = Math.max(kSums[i] + lSums[j], max);

        return max;
    }

    private static int sliceSum(int[] a, int start, int length) {
        int sum = 0;
        for (int i = start; i < start + length; i++)
            sum += a[i];
        return sum;
    }
}

