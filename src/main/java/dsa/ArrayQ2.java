package dsa;
import java.util.*;

public class ArrayQ2 {
    public static void main(String[] args) {
       // System.out.println(findRank2("cab"));;
        int [] A = {2,1,4,3,2};
      //  kthsmallest(A,3);
        int [] B={3,11,-1,5}; //{-1,3,5,11}
        int [] C={5, 17, 100, 11};
       // magicNumber(C);
        //absolouteNumber(C);
        int [] D={1, 3, 2};
       // inversionCount(D);
        int [] E={1, 3, 2, 3, 1};
        reversePairs(E);
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
        static int findSmallerInRight(String str, int low,int high) {
            int countRight = 0, i;
            for (i = low + 1; i <= high; ++i)
                if (str.charAt(i) < str.charAt(low))
                    ++countRight;
            return countRight;
        }
        static int findRank(String str) {
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

    static public int[] magicNumber(int[] A) {
        int N = A.length;
        int[] B = new int[2];

        // Sort the array in ascending order
        Arrays.sort(A);

        int maxMagicNumber = 0;
        int minMagicNumber = 0;

        // Calculate the maximum and minimum magic numbers
        for (int i = 0; i < N / 2; i++) {
            maxMagicNumber = (maxMagicNumber + Math.abs(A[i] - A[N - 1 - i])) % 1000000007;
            minMagicNumber = (minMagicNumber + Math.abs(A[2 * i] - A[2 * i + 1])) % 1000000007;
            System.out.println("Max:"+A[i]+" "+A[N-1-i]);
            System.out.println("Min:"+A[2*i]+" "+A[2*i+1]);
        }

        B[0] = maxMagicNumber;
        B[1] = minMagicNumber;
        for (int i=0;i<B.length;i++) System.out.println(B[i]);
        return B;
    }

    static public void absolouteNumber(int[] A) {
        int N = A.length;
        Arrays.sort(A);
        int minMagicNumber = Integer.MAX_VALUE;
        for (int i = 0; i < N / 2; i++) {
            minMagicNumber = Math.min(minMagicNumber ,Math.abs(A[2 * i] - A[2 * i + 1])) % 1000000007;
            System.out.println("Min:"+A[2*i]+" "+A[2*i+1]);
        }
        System.out.println("minMagicNumber "+minMagicNumber);
    }

    static int mod = 1000000007;
    static long inversionCount = 0l;
    public static int inversionCount(int[] A) {
        int n = A.length;
        mergeSort(A,0,n-1);
        return (int) inversionCount;
    }

    public static void mergeSort(int[] A, int s, int e){
        if(s >= e) return;
        int mid = (s+e)/2;
        mergeSort(A,s,mid);
        mergeSort(A,mid+1,e);
        merge(A,s,mid,e);
    }

    public static void merge(int[] A, int s, int mid, int e){
        int n1 = mid - s + 1; //number of elements in array1
        int n2 = e - mid;//number of elements in array2
        int A1[] = new int[n1];
        int A2[] = new int[n2];
        int index = 0;

        //filling A1 and A2
        for(int i = s; i <= mid; i++)
            A1[index++] = A[i];

        index = 0;
        for(int i = mid+1; i <= e; i++)
            A2[index++] = A[i];

        int i = 0, j = 0; // i and j referncing A1 and A2 array respectively
        index = s; // important

        while(i < n1 && j < n2){
            if(A1[i] <= A2[j]) // equals to condition for equal elements
                A[index++] = A1[i++];
            else{
                inversionCount = (inversionCount + (A1.length - i))%mod; //here fulfilling problem statement condition
                A[index++] = A2[j++];
            }
        }
        //if some elements still remaining after traversal
        if(i < n1){
            while(i < n1){
                A[index++] = A1[i++];
            }
        }else{
            while(j < n2){
                A[index++] = A2[j++];
            }
        }
    }

    public static void reversePairs(int[] A){
        int n = A.length;
        mergeSort2(A,0,n-1);
        System.out.println("reverse pairs:"+count);
    }
    public static int count = 0;
    public static void mergeSort2(int[] A, int s, int e){
        if(s >= e) return;
        int mid = (s+e)/2;
        mergeSort(A,s,mid);
        mergeSort(A,mid+1,e);
        merge2(A,s,mid,e);
    }
    public static void merge2(int[] A, int s, int mid, int e){
        int n1 = mid - s + 1;
        int n2 = e - mid;
        int A1[] = new int[n1];
        int A2[] = new int[n2];
        int index = 0;

        for(int i = s; i <= mid; i++)
            A1[index++] = A[i];

        index = 0;
        for(int i = mid+1; i <= e; i++)
            A2[index++] = A[i];

        int i = 0, j = 0;
        index = s; // important

        while(i < n1 && j < n2){
            if(A1[i] <= A2[j]) // equals to condition for equal elements
                A[index++] = A1[i++];
            else{
                    if (A1[i] >= 2*A2[j]) {
                        count = (count + (A1.length - i)) % mod;
                    }
                A[index++] = A2[j++];
            }
        }
        //if some elements still remaining after traversal
        if(i < n1){
            while(i < n1){
                A[index++] = A1[i++];
            }
        }else{
            while(j < n2){
                A[index++] = A2[j++];
            }
        }
    }
}

