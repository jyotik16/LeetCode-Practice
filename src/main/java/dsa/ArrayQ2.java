package dsa;
import java.util.*;

public class ArrayQ2 {
    public static void main(String[] args) {
        int [] A = {95,98,41,5};
       // System.out.println(solve(A));
       // System.out.println(findRank2("cab"));;
      //  int [] A = {2,1,4,3,2};
      //  kthsmallest(A,3);
        int [] B={3,11,-1,5}; //{-1,3,5,11}
        int [] C={5, 17, 100, 11};
       // magicNumber(C);
        //absolouteNumber(C);
        int [] D={1, 3, 2};
       // inversionCount(D);
        int [] E={1, 3, 2, 3, 1};
        int [] F={14046,57239,78362,99387,27609,55100,65536,62099,40820,33056,88380,78549,57512,33137,81212,32365,42276,65368,52459,74924,25355,76044,78056,45190,94365,58869,20611};
     //   reversePairs(E);
        int [] G={34100,17238,32724,89502,8098,99039,69429,55368,56113,43519,42546,13997,62184,74851,9656,88863};
       // reversePair3(G);
        int[][] I = {{1,3},{-2,2},{2,4}};
        //BClosetPoints(I,2);
       // findMaxSubarray();
        int [] J ={5,6,7,8,9,10,3,2,1};
        int [] K = {3, 9, 18, 20, 17, 5, 1};
       // System.out.println(searchInBitonicArray(K,20));
        int beggars = 5;
        int [][] donaters = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
      //  subContiousQuery(beggars,donaters);
        int[] subarray = {-2,-3,4,-1,-2,1,5,-3};
        int[] subarray2 = {-2,1,-3,4,-1,2,1,-5,4};
       // maxSubArraySumWithIndex(subarray2);
    }
    //flip
    public static  int[] flip(String A) {
        int n = A.length();
        int ans[] = new int[2];
        int start = 0;
        int ansStart = -1;
        int ansEnd = -1;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            if(A.charAt(i) == '0'){
                sum++;
                if(sum > max){
                    max = sum;
                    ansStart = start;
                    ansEnd = i;
                }
            }
            else if(A.charAt(i) == '1'){
                sum--;
                if(sum < 0){
                    sum = 0;
                    start = i+1;
                }
            }
        }
        ans[0] = ansStart+1;
        ans[1] = ansEnd+1;
        if(ans[0] == 0 && ans[1] == 0){
            return new int[0];
        }
        return ans;
    }
    public static void maxNonNegativeSubArray(){
        int[] subarray2 = {1, 2, 5, -7, 2, 3};
        int [] res = maxset(subarray2);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+" ");
        }
    }
    public static int[] maxset(int[] A) {
        int sum=0;
        int ans = Integer.MIN_VALUE;
        int n = A.length;
        int sumsp=0;
        int sumep=0;
        int anssp=0;
        int ansep=0;
        for(int i=0;i<A.length;i++){
            if(sum>=0){
                sum+=A[i];
                sumep=i;
            }
            else{
                sum=A[i];
                sumsp=i;
                sumep=i;
            }
            if(sum>ans){
                ans=sum;
                anssp=sumsp;
                ansep=sumep;
            }
        }
        int m = ansep - anssp + 1;
        int sub[] = new int[m];
        for(int i=anssp; i<=ansep;i++){
            sub[i] = A[i];
        }
        return sub;
    }

    static int maxSubArraySumWithIndex(int a[]) {
        int size = a.length;  int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;
        int si = 0; int ei = 0; int s =0;
        for (int i = 0; i < size; i++) {
                max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here) {
                max_so_far = max_ending_here;
                ei = i;
                si = s;
            }
            if (max_ending_here < 0) {
                max_ending_here = 0;
                s = i + 1;
            }
        }
        System.out.println("maximum sum:"+max_so_far);
        System.out.println("start index:"+(si));
        System.out.println("end index:"+(ei));
        return max_so_far;
    }
    //Kadane's Algo = continous sub-array max sum find
    static int maxSubArraySum(int a[]) {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;
        for (int i = 0; i < size; i++) {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }
    public static int[] subContiousQuery(int A, int[][] B) {
        int[] arr = new int[A];
        for(int i=0;i<B.length;i++){
            int si=B[i][0]-1;
            int ei=B[i][1]-1;
            int val=B[i][2];
            arr[si]+=val;
            if(ei+1<A){
                arr[ei+1]-=val;
            }
        }
        for(int i=1;i<A;i++){
            arr[i]=arr[i-1]+arr[i];
        }
        return arr;
    }
    public static void findMaxSubarray(){
        int A[] = {1,1,2,3,3,4,8,9,11,9,11,12,12,11,9,14,19,20,20};//8,14
        int B[] ={1,2, 4, 3, 2, 5, 3, 6}; //2,6
        int C[] = {1,1,10,10,15,10,15,10,10,15,10,15};//4,11
        int [] ans = subUnsort(C);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i]+" ");
        }
    }
    public static int[] subUnsort(int[] A) {
        int[] ans = {-1};
        int n = A.length;
        int r = -1, l = 0;
        //finding the index from right where array started unsorted
        for(int i = n-1; i > 0; i--){
            if(A[i] < A[i-1]){
                r = i;
                break;
            }
        }
        //finding the index frm left where array started unsorted
        for(int i = 0; i < n-1; i++){
            if(A[i+1] < A[i]){
                l = i;
                break;
            }
        }
        //finding max and min in between left and right
        int minElement = Integer.MAX_VALUE;
        int maxElement = Integer.MIN_VALUE;
        for(int i = l; i <= r && i < n; i++){
            minElement = Math.min(minElement,A[i]);
            maxElement = Math.max(maxElement,A[i]);
        }
        //iterating from left of the array to find correct index and from right to find the correct index
        //don't traverse the whole array again give TLE
        int i = 0, j = n-1;
        if(r != -1){
            while(A[i] <= minElement && i<=l){
                i++;
            }
            while(A[j] >= maxElement && j>=r){
                j--;
            }
        }
        else return ans;

        int[] ans2 = {i,j};
        return ans2;
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
        //[1,3,2,3,1]
        int n = A.length;
        System.out.println("reverse pairs: "+mergeSort2(A,0,n-1));;
    }
    public static int mergeSort2(int[] A, int s, int e){
        if(s >= e) return 0;
        int m = (s + e)/2;
        int sfh = mergeSort2(A, s, m); // sorted first half
        int ssh = mergeSort2(A, m+1, e); // sorted second half

        // do reverse check b4 merging two already sorted halves
        int count = 0;
        int i = s; // left part head ptr
        int j = m+1; // right part head ptr
        while(i <= m && j <= e){
            if((long)A[i] > (long)2*A[j]){
                // since left part sorted , remain elem also satisfy abv conditn
                count += (m - i + 1); // remaining elems of left part
                j++;
            }else{
                i++;
            }
        }
        // then merge
        merge2(A, s, m, e);
        return (sfh + ssh + count);
    }
    public static void merge2(int[] A, int s, int m, int e){
        int[] C = new int[e - s + 1];
        int i = s;
        int j = m+1;
        int k = 0;
        // 1. merge two sorted arrays using 2 ptr
        while(i <= m && j <= e){
            if(A[i] > A[j]) C[k++] = A[j++];
            else C[k++] = A[i++];
        }
        // 2. fill remaining elements
        while(i <= m) C[k++] = A[i++];
        while(j <= e) C[k++] = A[j++];

        // 3. copy merged sorted array from s to e on A[]
        i = s;
        j = 0;
        while( i <= e) A[i++] = C[j++];
    }

    public static int merge3(int[] A, int s, int mid, int e){
        int count = 0;
        int i = s; // left part head ptr
        int j = mid+1; // right part head ptr
        while(i <= mid && j <= e){
            if((long)A[i] > (long)2*A[j]){
                // since left part sorted , remain elem also satisfy abv conditn
                count += (mid - i + 1); // remaining elems of left part
                j++;
            }else{
                i++;
            }
        }

        int n1 = mid - s + 1; //number of elements in array1
        int n2 = e - mid;//number of elements in array2
        int A1[] = new int[n1];
        int A2[] = new int[n2];
        int index = 0;

        //filling A1 and A2
        for(i = s; i <= mid; i++)
            A1[index++] = A[i];

        index = 0;
        for(i = mid+1; i <= e; i++)
            A2[index++] = A[i];

        i = 0 ;
        j = 0; // i and j referncing A1 and A2 array respectively
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
        return count;
    }

    static int ans=0;
    public static void mergeSort3(int[] A, int s, int e){
        if(s >= e) return;
        int mid = (s+e)/2;
        mergeSort3(A,s,mid);
        mergeSort3(A,mid+1,e);
        ans += merge3(A,s,mid,e);
    }

    public static void reversePair3(int []A){
        int n = A.length;
       mergeSort3(A,0,n-1);
       System.out.println("ans:"+ans);
    }

    //Find an element in Bitonic array
    public static int searchInBitonicArray(int []A,int key){
        int n = A.length;
        int bitonicIndex = findBitonicPointIndex(A,0,n-1);
        if (A[bitonicIndex] == key){
            return bitonicIndex;
        }
        if (A[bitonicIndex]<key){
            return -1; //In bitonic serires only A[bitonicIndex] has max value;
        }
        int temp1 = binarySearchInAescendingOrder(A,key,bitonicIndex-1);
        if (temp1!=-1)
            return temp1;
        int temp2= binarySearchInDescendingOrder(A,key,bitonicIndex+1);
        if (temp2!=-1)
            return temp2;
        return -1;
    }
    public static int findBitonicPointIndex(int[] A,int l,int r){
        int bitonicPoint = 0; int n = A.length-1;
        int mid = (l+r)/2;
        if(A[mid]>A[mid-1] && A[mid]>A[mid+1]){
            return mid;
        } else {
            if (A[mid] > A[mid - 1] && A[mid] < A[mid + 1])
                {
                    bitonicPoint = findBitonicPointIndex(A, mid, r);
                }
            else if (A[mid] < A[mid - 1] && A[mid] > A[mid + 1])
                {
                    bitonicPoint = findBitonicPointIndex(A, l, mid);
                }
        }
        return bitonicPoint;
    }
    public static int binarySearchInAescendingOrder(int []A,int key,int range){
        int l=0; int r=range;
        while(l<=r){
        int mid = (l+r)/2;
        if (A[mid] == key){
            return mid;
        } else if (A[mid] < key) {
            l = mid + 1;
        }else {
            r = mid - 1;
        }
        }
        return -1;
    }
    public static int binarySearchInDescendingOrder(int []A,int key,int range){
        int l=range; int r=A.length-1;
        while(l<=r){
            int mid = (l+r)/2;
            if (A[mid] == key){
                return mid;
            } else if (A[mid] < key) {
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return -1;
    }
}


