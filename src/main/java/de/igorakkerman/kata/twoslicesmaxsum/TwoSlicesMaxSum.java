package de.igorakkerman.kata.twoslicesmaxsum;

import static java.lang.Math.max;

public class TwoSlicesMaxSum {
    public static int twoSlicesMaxSum(int[] a, int k, int l) {
        if (a.length < k + l)
            return -1;

        return klMaxSum(a, k, l, kSums(a, k, l));
    }

    private static int[] kSums(int[] a, int k, int l) {
        int[] kMaxSums = new int[a.length - l];

        int kSum = sliceSum(a, 0, k - 1);
        kMaxSums[k - 1] = kSum;

        for (int upperBound = k; upperBound < a.length - l; upperBound++) {
            kSum = kSum - a[upperBound - k] + a[upperBound];
            kMaxSums[upperBound] = max(kMaxSums[upperBound - 1], kSum);
        }

        return kMaxSums;
    }

    private static int klMaxSum(int[] a, int k, int l, int[] kMaxSums) {
        int lowerBound = a.length - l;
        int lSum = sliceSum(a, lowerBound, a.length - 1);
        int maxSum = Integer.MIN_VALUE;

        do {
            --lowerBound;
            maxSum = max(maxSum, kMaxSums[lowerBound] + lSum);
            lSum = lSum - a[lowerBound + l] + a[lowerBound];
        } while (lowerBound >= k);

        return maxSum;
    }

    private static int sliceSum(int[] a, int lowerBound, int upperBound) {
        int sum = 0;
        for (int i = lowerBound; i <= upperBound; i++)
            sum += a[i];
        return sum;
    }
}

