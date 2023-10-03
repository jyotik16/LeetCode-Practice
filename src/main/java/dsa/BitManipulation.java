package dsa;

import java.util.*;

public class BitManipulation {

    public static void main(String[] args) {
        int[] ar = {1, 3, 5};
        int A = 18;
        int B = 2;
        int [] br ={53, 39, 88};
        //    System.out.println(A>>B/2);
        // allSubsets(ar);
        //  System.out.println(solve(15,2));
        // System.out.println(checkbit(1,1));
        //  sumOfAllSubsetsInArray(ar);
        //  System.out.println(divide(12,-5));
        // System.out.println(sumBitDifferences(ar,ar.length));
        //System.out.println(maxAndValue(br));
        float a = (12<<3);
       // System.out.println(a);
       // System.out.println(divide2(12,4));
      // generateGrayCodes(3);
        String [] req_skills = {"java","nodejs","reactjs"};
        List<List<String>> people = new ArrayList<>();
        people.add(Arrays.asList("java"));
        people.add(Arrays.asList("nodejs"));
        people.add(Arrays.asList("nodejs","reactjs"));
      //  smallestSufficientTeam(req_skills,people);
     //   reverseBit();
     //   sumOfpairXORSUM();
        starngeEquality();
      //  printAllsubsets();
    }

    private static void printAllsubsets() {
        int [] A = {3,2,1};
        printAllsubsets(A);
    }

    private static void printAllsubsets(int[] list) {
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            A.add(list[i]);
        }
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int n = A.size();
        int m = (int)Math.pow(2,n);
        for (int i = 0; i < m; i++) {
            ArrayList<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    subset.add(A.get(j));
                }
            }

            result.add(subset);
        }
        System.out.println(result);
        Collections.sort(result, (a, b) -> {
            for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
                int cmp = Integer.compare(a.get(i), b.get(i));
                if (cmp != 0) {
                    return cmp;
                }
            }
            return Integer.compare(a.size(), b.size());
        });
       // return result;
        System.out.println(result);


    }

    private static void starngeEquality() {
        int A = 5;
        starngeEquality(A);
    }

    private static void starngeEquality(int A) {
            int smallest = 0 , largest = 0;
            boolean large = true;
            // X XOR A == X + A
            for(int i = 0 ; i < 31 ; i++)
            {
                int value = 1 << i;
              //  if( value < A && ( (A >> i ) & 1 ) == 0 && smallest < A)
                if( value < A && checkXOR(value,A) && smallest < A)
                    smallest += 1 << i;

                if( value > A && ( (A >> i ) & 1 ) == 0 && large )
                {
                    largest = 1 << i;
                    large = false;
                }
            }
            //return smallest + largest;
            System.out.println(smallest+largest);
    }

    static boolean checkXOR(int value,int a){
        int sum = value + a;
        int xor = value ^ a;
        if (sum == xor)
            return true;
        else
            return false;
    }

    private static void sumOfpairXORSUM() {
        int [] A = {1, 3, 5};  // 12,8
        int [] B = {1, 2, 3}; //(1,2) (2,3) (1,3)
        sumOfpairXORSUM(A);
        sumOfpairXORSUM_differentBit(A);
    }

    private static void sumOfpairXORSUM(int[] A) {
        long ans=0;
        int n=A.length;
        long modNum = 1000000007;
        for(int i=0;i<32;i++){
            long subCount=0, count=0;

            for(int j=0;j<n;j++){
                if(checkBit(A[j], i)==1){
                    subCount++;
                }
            }

            count = (subCount*(n-subCount));
            ans =(ans + ((count*(1<<i)))%modNum)%modNum;
        }
        ans = (int)ans;
        System.out.println(ans);
    }
    public static int checkBit(int x, int y){
        if((x&(1<<y))!=0){
            return 1;
        }
        else{
            return 0;
        }
    }

    private static void sumOfpairXORSUM_differentBit(int[] A) {
        long ans=0;
        int n=A.length;
        long modNum = 1000000007;
        for(int i=0;i<32;i++){
            long subCount=0, count=0;

            for(int j=0;j<n;j++){
                if(checkBit(A[j], i)==1){
                    subCount++;
                }
            }

            count = (subCount*(n-subCount));
            ans = (ans+count%modNum)%modNum;
        }
        ans = (int)(2*ans)%modNum;
        System.out.println(ans);
    }


    private static void reverseBit() {
        int num = 3;
        reverseBit(num);
    }

    private static void reverseBit(int a) {
        long ans=0;
        for(int i=0;i<32;i++){
            ans=ans<<1;
            ans=ans|(a&1);
            a=a>>1;
        }
        System.out.println(ans); //3221225472
    }


    // Time O(people * 2^skill)
    //Space O(2^skill)
    public static int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length, m = people.size();
        HashMap<String, Integer> skill_index = new HashMap<>();
        for (int i = 0; i < n; ++i)
            skill_index.put(req_skills[i], i);
        List<Integer>[] dp = new List[1 << n];
        dp[0] = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            int cur_skill = 0;
            for (String s : people.get(i))
                cur_skill |= 1 << skill_index.get(s);
            for (int prev = 0; prev < dp.length; ++prev) {
                if (dp[prev] == null) continue;
                int comb = prev | cur_skill;
                if (dp[comb] == null || dp[prev].size() + 1 < dp[comb].size()) {
                    dp[comb] = new ArrayList<>(dp[prev]);
                    dp[comb].add(i);
                }
            }
        }
        for (int i = 0; i <dp.length ; i++) {
            System.out.println(dp[i]);
        }
        return dp[(1 << n) - 1].stream().mapToInt(i -> i).toArray();
    }
    //T:O(2^N) S:O(2^N)
    static ArrayList<String> generateGrayByRec(int n) {
        if (n <= 0)
        {
            ArrayList<String> temp = new ArrayList<String>(){{add("0");}};
            return temp;
        }
        if(n == 1)
        {
            ArrayList<String> temp = new ArrayList<String>(){{add("0");add("1");}};
            return temp;
        }
        ArrayList<String> recAns = generateGrayByRec(n - 1);
        ArrayList<String> mainAns = new ArrayList<String>();
        for(int i = 0; i < recAns.size(); i++)
        {
            String s = recAns.get(i);
            mainAns.add("0" + s);

        }
        for(int i = recAns.size() - 1; i >= 0; i--)
        {
            String s = recAns.get(i);
            mainAns.add("1" + s);
        }
        return mainAns;
    }
    static void generateGrayCodes(int n){
        ArrayList<Integer> ans = generateGrayByBitM(n);
        for (Integer i:ans
             ) {
            System.out.println(Integer.toBinaryString(i));
        }
    }
    static ArrayList<Integer> generateGrayByBitM(int n) {
       if(n <= 1)
        {
            ArrayList<Integer> temp = new ArrayList<Integer>(){{add(0);}};
            if(n == 1){
                temp.add(1);
            }
            return temp;
        }
        ArrayList<Integer> recAns = generateGrayByBitM(n - 1);
        ArrayList<Integer> mainAns = new ArrayList<Integer>();
        for (Integer s:recAns){
            mainAns.add(s); //add normal seq
        }
        int bm = (1<<(n-1));
        for (int i = recAns.size()-1; i >=0 ; i--) {
            Integer val = recAns.get(i);
            mainAns.add(val | bm);
        }
        return mainAns;
    }
    public static int solve(int A, int B) {
        int ans = Integer.MAX_VALUE;
        for(int i=1; i<31; i++){
            int count = 0;
            for(int j=0; j<32; j++){
                if(checkbit(i, j)){
                    count++;
                }
            }
            if(count == B){
                ans = Math.min(ans, A^i);
            }
        }
        return ans;
    }
    static boolean  checkbit(int n,int k){
        if ((n & (1 <<k)) != 0)
            return true;
        else
            return false;
    }
    //Time Complexity: O(N*2^N)
    //Auxiliary Space: O(N*N)
    private static void allSubsets(int []ar) {
        int set_size = ar.length;
        long N = (long)Math.pow(2,ar.length);
        ArrayList<ArrayList<Integer>> sets = new ArrayList<>();
        for(int i=0;i<N;i++){
            ArrayList<Integer> subsets = new ArrayList<>();
            for (int j = 0; j < set_size; j++) {
                if (checkbit(i,j) == true){
                    subsets.add(ar[j]);
                }
            }
            sets.add(subsets);
        }
        for (ArrayList<Integer> list:sets) {
            System.out.println(list);
        }
    }
    //Time Complexity: O(N*2^N)
    //Auxiliary Space: O(1)
    private static void sumOfAllSubsetsInArray(int []ar) {
        int set_size = ar.length;
        long N = (long)Math.pow(2,ar.length);
        //ArrayList<ArrayList<Integer>> sets = new ArrayList<>();
        int sum = 0;
        for(int i=0;i<N;i++){
            //ArrayList<Integer> subsets = new ArrayList<>();
            int subsum =0;
            for (int j = 0; j < set_size; j++) {
                if (checkbit(i,j) == true){
                    subsum += ar[j];
                    //subsets.add(ar[j]);
                }
            }
            sum +=subsum;
           // sets.add(subsets);
        }
       /* for (ArrayList<Integer> list:sets) {
            System.out.println(list);
        }*/
        System.out.println("Total sum of all subsets in array is "+sum);
    }
    //T:O(log(a))
    public static long divide2(long dividend, long divisor){

// Calculate sign of divisor
// i.e., sign will be negative
// only if either one of them
// is negative otherwise it
// will be positive
        long sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
        dividend = Math.abs(dividend);   divisor = Math.abs(divisor);
        long quotient = 0, temp = 0;

// test down from the highest
// bit and accumulate the
// tentative value for
// valid bit
// 1<<31 behaves incorrectly and gives Integer
// Min Value which should not be the case, instead
        // 1L<<31 works correctly.
        for (int i = 31; i >= 0; --i) {

            if (temp + (divisor << i) <= dividend) {
                temp += divisor << i;
                quotient |= 1L << i;
            }
        }
        if (sign == -1)
            quotient = -quotient;
        return quotient;
    }

    //T:O(a/b)
    static long divide(long dividend, long divisor) {
        // Calculate sign of divisor i.e.,
        // sign will be negative only if
        // either one of them is negative
        // otherwise it will be positive
        long sign = ((dividend < 0) || (divisor < 0)) ? -1 : 1;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        long quotient = 0;

        while (dividend >= divisor) {
            dividend -= divisor;
            ++quotient;
        }
        // if the sign value computed earlier is -1 then
        // negate the value of quotient
        if (sign == -1)
            quotient = -quotient;

        return quotient;
    }

    static int sumBitDifferences(int arr[], int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int j = 0; j < n; j++)
                if ((arr[j] & (1 << i)) != 0)
                    count++;
            ans += (count * (n - count) * 2);
        }
        return ans;
    }

    static public int maxAndValue(int[] A) {
        int n = A.length;
        int maxAnd = 0;
        for (int bit = 31; bit >= 0; bit--) {
            int count = 0;

            for (int i = 0; i < n; i++) {
                if ((A[i] & (1 << bit)) != 0) {
                    count++;
                }
            }
            if (count >= 2) {
               maxAnd = maxAnd | (1 << bit); // bitwise [|] == sum operator
               // maxAnd = maxAnd + (1<<bit);
                for (int i = 0; i < n; i++) {
                    if ((A[i] & (1 << bit)) == 0) {
                        A[i] = 0;
                    }
                }
            }
        }
        return maxAnd;
    }
    //Leetcode 137
    //T: O(N)
    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;

        for (int num : nums) {
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }

        return ones;
    }
    //Leetcode 137
    //T: O(N*32)
    public int singleNumber2(int[] nums) {
        int N = nums.length;
        int count = 0;
        int ans = 0;
       for(int i=0;i<32;i++){
           count = 0;
           for(int j=0;j<N;j++){
               if(checkbit2(nums[j],i) == true){
                   count++;
               }
           }
            if(count % 3 == 1){
                ans = ans + (1<<i);
            }
       } 
       return ans;
    }

    boolean checkbit2(int n,int k){
        if ((n & (1 <<k)) != 0)
            return true;
        else
           return false;
        }
}


