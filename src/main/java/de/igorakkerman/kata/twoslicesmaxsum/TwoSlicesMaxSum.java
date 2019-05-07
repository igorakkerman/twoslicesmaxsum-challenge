package de.igorakkerman.kata.twoslicesmaxsum;

import static java.lang.Math.max;

public class TwoSlicesMaxSum {
    public static int twoSlicesMaxSum(int[] a, int k, int l) {
        if (a.length < k + l)
            return -1;

        int[] kMaxSums = new int[a.length - l];

        kSums(a, k, l, kMaxSums);
        return klSums(a, k, l, kMaxSums);
    }

    private static void kSums(int[] a, int k, int l, int[] kMaxSums) {
        int kSum = sliceSum(a, 0, k - 1);
        kMaxSums[k - 1] = kSum;
        for (int end = k; end < a.length - l; end ++) {
            kSum = kSum - a[end - k] + a[end];
            kMaxSums[end] = max(kMaxSums[end - 1], kSum);
        }
    }

    private static int klSums(int[] a, int k, int l, int[] kMaxSums) {
        int lSum = sliceSum(a, a.length - l, a.length - 1);
        int maxSum = kMaxSums[a.length - l - 1] + lSum;
        for (int start = a.length - l - 1; start >= k; start--) {
            lSum = lSum - a[start + l] + a[start];
            maxSum = max(maxSum, kMaxSums[start - 1] + lSum);
        }
        return maxSum;
    }

    private static int sliceSum(int[] a, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++)
            sum += a[i];
        return sum;
    }
}

