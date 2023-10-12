package dsa;

import java.util.*;

public class Sort {
    public static void main(String[] args) {
        sumOfDifferenceofAllEmptySubsets();
    }

    private static void sumOfDifferenceofAllEmptySubsets() {
        int [] A = {3,5,10};
       // sumOfDifferenceofAllNonEmptySubsets(A);
        findSubsetsUsingBitM(A);
    }

    private static void sumOfDifferenceofAllNonEmptySubsets(int[] A) {
        Arrays.sort(A);
        double ans = 0;
        int n = A.length;
        double mod = 1000000007;
        for(int i=0; i<n; i++){
            double maxPow = Math.pow(2, i)%mod;
            double minPow = Math.pow(2, n - i - 1)%mod;
            ans = (ans + (A[i] * (maxPow - minPow + mod) % mod)) % mod;
        }
       // return (int)ans;
        System.out.println(ans);

    }
    public static void findSubsetsUsingBitM(int[] nums)
    {
        int n = nums.length;
        int max ,min;
        int sumDiff = 0;
        List<List<Integer>> sets = new ArrayList<>();
        for (int i = 1; i < (1 << n); i++) {
            List<Integer> subset = new ArrayList<>();
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
             for (int j = 0; j < n; j++) {
                 if ((i & (1 << j)) != 0) {
                   // System.out.print(nums[j] + " ");
                     subset.add(nums[j]);
                     max = Math.max(max,nums[j]);
                     min = Math.min(min,nums[j]);
                }
            }
            sumDiff += (max-min);
            sets.add(subset);
           // System.out.println();
        }
        System.out.println("sumDiff:"+sumDiff);
        System.out.println(sets); //[ [3], [5], [3, 5], [10], [3, 10], [5, 10], [3, 5, 10]]
        int ans = 0;
        for (int i = 0; i < sets.size(); i++) {

        }
    }
}
