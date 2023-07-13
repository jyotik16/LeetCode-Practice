package dsa;
import java.util.*;

public class ArrayQ2 {
    public static void main(String[] args) {
       // System.out.println(findRank2("cab"));;
        int [] A = {2,1,4,3,2};
        kthsmallest(A,3);
    }

        public static int solve(int[] A) {
            int size = 100001;
            HashSet<Integer> set = new HashSet<>();
            HashSet<Integer> ans = new HashSet<>();
            for(int i=0; i<A.length; i++)
                set.add(A[i]);
            for(int i=2; i<size; i++){
                if(isPrime(i)==1) {
                    for(int j=i; j<size; j+=i) {
                        if(set.contains(j)) {
                            ans.add(i);
                        }
                    }
                }
            }
            return ans.size();
        }
        public static int isPrime(int A){
            if(A==2 || A==3){
                return 1;
            }
            if(A==1){
                return 0;
            }
            for(int i=2; i*i<=A; i++){
                if(A%i==0){
                    return 0;
                }
            }
            return 1;
        }

        public static int[] countOfDivisor(int [] A){
            int[] ans = new int[A.length];
            int n = A.length;
            int max = Integer.MIN_VALUE;
            for(int i=0; i<n; i++) {
                max = Math.max(max, A[i]);
            }
            int[] temp = new int[max + 1];
            Arrays.fill(temp, 2);
            for(int i=2; i<=max; i++) {
                for(int j=2*i; j<=max; j+=i) {
                    temp[j]+=1;
                }
            }
            for(int i=0;i<n; i++) {
                if(A[i]==1) {
                    ans[i] = 1;
                }
                else {
                    ans[i] = temp[A[i]];
                }
            }
            return ans;
        }
        public static int fact2(int n) {
            if (n <= 1) {
                return 1;
            }
            return n * fact(n - 1);
        }

        static int fact(int n)
        {
            return (n <= 1) ? 1 : n * fact(n - 1);
        }
        static int findSmallerInRight(String str, int low,int high)
        {
            int countRight = 0, i;
            for (i = low + 1; i <= high; ++i)
                if (str.charAt(i) < str.charAt(low))
                    ++countRight;
            return countRight;
        }
        static int findRank(String str)
        {
            int len = str.length();
            int mul = fact(len);
            int rank = 1;
            int countRight;

            for (int i = 0; i < len; ++i) {
                mul /= len - i;
                countRight = findSmallerInRight(str, i, len - 1);
                rank += countRight * mul;
            }
            return rank;
        }

    static void updatecount(int[] count, char ch) {   int i;
        for (i = ch; i < 256; ++i)
            --count[i];
    }
    static void populateAndIncreaseCount(int[] count, String str) {
        int i;
        for (i = 0; i < str.length(); ++i)
            ++count[str.charAt(i)];
        for (i = 1; i < 256; ++i)
            count[i] += count[i - 1];
    }
    static int findRank2(String str) {
        int len = str.length();
        int mul = fact(len);
        int rank = 1;
        int countRight;
        int [] freq = new int[256];
        populateAndIncreaseCount(freq,str);
        for (int i = 0; i < len; ++i) {
            mul /= len - i;
            rank += freq[str.charAt(i)-1] * mul;
            updatecount(freq,str.charAt(i));
        }
        return rank;
    }

    //Kth smallest element using selection sort
    static public int kthsmallest(int[] A, int B) {
        int n = A.length;
        for(int i=0;i<B; i++){
            int minele = A[i];
            int minidn = i;
            for(int j=i;j<n;j++){
                if(A[j]<minele){
                    minele = A[j];
                    minidn = j;
                }
            }
            int temp = A[i];
            A[i] = minele;
            A[minidn] = temp;
        }
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i]+" ");
        }
        return A[B-1];
    }
}

