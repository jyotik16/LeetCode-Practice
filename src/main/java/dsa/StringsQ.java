package dsa;

import java.util.*;

public class StringsQ {
    public static void main(String[] args) {
       // System.out.println(lengthOfLongestSubstring("abcabcbb"));
       // System.out.println(multiply("123","26"));
      //  numDecodings("226");
      //  System.out.println(simplifyPath("/a/./b/../../c/"));
      //  System.out.println(strStr("butsaf","sad"));;
      //  System.out.println(findAnagrams("cbaebabacd","abc"));
      //  StringBuilder id = new StringBuilder("FNT699754632");
      //  String [] ids = id.indexOf("_")==-1?id.toString().split(""):id.toString().split("_");
      //  System.out.println(ids[0]+" "+ids[1]);
      //  System.out.println(ids[1].substring(0,1).toString().startsWith("T"));
       // System.out.println(ids[1].substring(1).toString());
       // shopPenalty("YYNY");
        rabinKrap();
    }

    private static void rabinKrap() {
        String A="ABBCABB";
        String B="ABB";
       // System.out.println(solve(A.toLowerCase(),B.toLowerCase()));
        search(B.toLowerCase(),A.toLowerCase(),29);
    }

    public static int solve(String A, String B) {
        int n = A.length();
        int m = B.length();
        int prime = 29;
        int mod = (int)(1e9 + 7);

        long[] p_pow = new long[n];
        p_pow[0] = 1;
        for (int i = 1; i < n; i++) {
            p_pow[i] = (p_pow[i - 1] * prime) % mod;
        } //[1, 29, 841, 24389, 707281, 20511149, 594823321, 249876190]

        long[] h = new long[n + 1];
        for (int i = 0; i < n; i++){
            h[i + 1] = (h[i] + (A.charAt(i) - 'a' + 1) * p_pow[i]) % mod;
        } //[0, 1, 59, 1741, 74908, 782189, 41804487, 231451122] // A B B C A B B
        long hash_B = 0;
        for (int i = 0; i < m; i++) {
            hash_B = (hash_B + (B.charAt(i) - 'a' + 1) * p_pow[i]) % mod;
        } //[1,58,1683] = 1741 [A,B,B]

        int ans = 0;
        for (int i = 0; i + m - 1 < n; i++) {
            long curr_hash = (h[i + m] + mod - h[i]) % mod;
            if (curr_hash == hash_B * p_pow[i] % mod)
                ans += 1;
        }

        return ans;
    }

    public final static int d = 256;

    static void search(String pat, String txt, int primeNum) {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        int pat_hashvalue = 0; // hash value for pattern
        int txt_hashvalue = 0; // hash value for txt
        int pow = 1;

        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < M - 1; i++)
            pow = (pow * d) % primeNum;
        // iterate over pat length
        for (i = 0; i < M; i++) {
            pat_hashvalue = (d * pat_hashvalue + pat.charAt(i)) % primeNum;
            txt_hashvalue = (d * txt_hashvalue + txt.charAt(i)) % primeNum;
        }

        for (i = 0; i <= N - M; i++) {

            // Check the hash values of current window of // text and pattern. If the hash values match
            // then only check for characters one by one
            if (pat_hashvalue == txt_hashvalue) {
                for (j = 0; j < M; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }
                if (j == M)
                    System.out.println("Pattern found at index " + i);
            }
            // Calculate hash value for next window of text:
            // Remove leading digit, add trailing digit
            if (i < N - M) {
                txt_hashvalue = (d * (txt_hashvalue - txt.charAt(i) * pow) + txt.charAt(i + M)) % primeNum;
                if (txt_hashvalue < 0)
                    txt_hashvalue = (txt_hashvalue + primeNum);
            }
        }
    }
    // 2483
    public static int shopPenalty(String customers) {
       // return bruteforce(customers);
       // return optimized(customers);
        return spaceOptimized(customers);
    }

    private static int spaceOptimized(String customers) {
        int n = customers.length();
        int min_penalty = 0;
        int min_hour = 0;
        int penalty =0;
        for (int i = 0; i < n; i++) {
            if(customers.charAt(i) == 'Y')
                penalty++;
        }
        min_penalty = penalty;
        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'Y'){
                penalty--;
            }else {
                penalty++;
            }
            if (penalty<min_penalty){
                min_penalty = penalty;
                min_hour = i+1;
            }
        }
        return min_hour;
    }

    private static int optimized(String customers) {
        int n = customers.length();
        int[] prefixN = new int[n+1];
        int[] suffixY = new int[n+1];

        prefixN[0] = 0;
        suffixY[n] = 0;
        int min_penality = Integer.MAX_VALUE;
        int min_hour = Integer.MIN_VALUE;


        for (int i = 1; i <=n; i++) {
            if (customers.charAt(i-1) == 'N'){
                prefixN[i] = prefixN[i-1] + 1;
            }else{
                prefixN[i] = prefixN[i-1];
            }
        }
        for (int i = n-1; i >=0; i--) {
            if (customers.charAt(i) == 'Y'){
                suffixY[i] = suffixY[i+1] + 1;
            }else{
                suffixY[i] = suffixY[i+1];
            }
        }

        for (int i = 0; i <=n ; i++) {
            int currPenality = prefixN[i] + suffixY[i];
            if (currPenality<min_penality){
                min_penality = currPenality;
                min_hour = i;
            }
        }

        return min_hour;
    }

    private static int bruteforce(String customers) {
        int n = customers.length();
        int min_penality = Integer.MAX_VALUE;
        int min_hour = Integer.MIN_VALUE;
        int penality =0;
        for (int i = 0; i <=n; i++) {
            int j = i-1;
            penality = 0;
            while(j>=0){
                if (customers.charAt(j) == 'N')
                    penality++;
                j--;
            }
            j=i;
            while(j<n){
                if (customers.charAt(j) == 'Y')
                    penality++;
                j++;
            }
            if(penality<min_penality){
                min_hour = i;
                min_penality = penality;
            }
        }
        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'N')
                penality++;
        }
        if(penality<min_penality){
            min_hour = n;
            min_penality = penality;
        }
        return min_hour;
    }

    //438
    public static List<Integer> findAnagrams(String txt, String pat) {
        List<Integer> count = new ArrayList<>();
        HashMap<Character,Integer> map  = new HashMap<>();
        for (int i = 0;i<pat.length();i++){
            char ch  = pat.charAt(i);
            if (map.containsKey(ch)){
                map.put(ch,map.getOrDefault(ch,0)+1);
            }else{
                map.put(ch,1);
            }
        }
        int k = pat.length(); int i=0;
        for (int j=0;j<txt.length() && i<txt.length();j++){
            char ch = txt.charAt(j);
            if (map.containsKey(ch)){
                map.put(ch,map.get(ch)-1);
            }
            if (j-i+1 == k){
                if (countAllcharacters(map)){
                    count.add(i);
                }
                char ch2 = txt.charAt(i);
                if (map.containsKey(ch2)){
                    map.put(ch2,map.get(ch2)+1);
                }
                i++;
            }
        }
        return count;
    }
    private  static boolean countAllcharacters(HashMap<Character, Integer> map) {
        for (Character set:map.keySet()){
            if (map.get(set) != 0)
                return false;
        }
        return true;
    }
    //28
    public static int strStr2(String haystack, String needle) {
        if(needle.length()>haystack.length()) return -1;
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }
    //28
    public static int strStr(String haystack, String needle) {
            int l1 = haystack.length(), l2 = needle.length();
            if (l1 < l2) {
                return -1;
            } else if (l2 == 0) {
                return 0;
            }
            int threshold = l1 - l2;
            for (int i = 0; i <= threshold; ++i) {
                if (haystack.substring(i,i+l2).equals(needle)) {
                    return i;
                }
            }
            return -1;
    }
    //38
    public static String countAndSay2(int n) {
        if(n == 1) return "1";
        StringBuilder res = new StringBuilder();
        String s = countAndSay2(n-1);

        for(int i=0, c=1; i < s.length(); i++, c++){
            // if next digit is different, then append the count so far `c` and the digit itself, then set count `c` to zero
            //Added condition to see if it's the last character to terminate and count the freq so far
            if(i==s.length()-1 || s.charAt(i+1) != s.charAt(i)){
                res.append(c).append(s.charAt(i));
                c = 0;
            }
        }
        return res.toString();
    }
    //38
    public static String countAndSay(int n) {
        if(n == 1) return "1";
        String val = "1";
        for (int i=0;i<n-1;i++){
            char ch1 = val.charAt(0);
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 1;j<val.length();j++){
                char ch2 = val.charAt(j);
                if(ch1 != ch2){
                    sb.append(count);
                    sb.append(ch1);
                    count = 0;
                    ch1 = val.charAt(j);
                }
                count++;
            }
            sb.append(count); sb.append(ch1);
            val = sb.toString();
        }
        return val;
    }
    //71
    public static String simplifyPath(String path) {

        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(dir)) stack.push(dir);
        }
        String res = "";
        for (String dir : stack) res = "/" + dir + res;
        return res.isEmpty() ? "/" : res;

    }
    //71
    public String simplifyPath2(String path) {
        Stack<String> stack = new Stack<>();
        String[] p = path.split("/");
        for (int i = 0; i < p.length; i++) {
            if (!stack.empty() && p[i].equals(".."))
                stack.pop();
            else if (!p[i].equals(".") && !p[i].equals("") && !p[i].equals(".."))
                stack.push(p[i]);
        }
        List<String> list = new ArrayList(stack);
        return "/"+String.join("/", list);
    }
    //91
    public int numDecodings2(String s) {
        Integer[] memo = new Integer[s.length() + 1];
        return numDecodings2(s, 0, memo);
    }
    private int numDecodings2(String s, int index, Integer[] memo) {
        if (index == s.length()) {
            return 1;
        }
        if (s.charAt(index) == '0') {
            return 0;
        }
        if (memo[index] != null) {
            return memo[index];
        }
        int way1 = numDecodings2(s, index + 1, memo);
        int way2 = 0;
        if (index < s.length() - 1 && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
            way2 = numDecodings2(s, index + 2, memo);
        }
        memo[index] = way1 + way2;
        return memo[index];
    }
    //91
    public static int numDecodings(String s) {
        printDecodings(s,"");
        return 0;
    }
    //time limit exceed
    public static void printDecodings(String str,String asf) {
        if(str.length() == 0) {
            System.out.println(asf);
            return;
        }
        int n1 = str.charAt(0) - '0';
        if(n1 == 0) {
            return;
        }

        char ch1 = (char)(n1 + 'a' - 1);
        String roq1 = str.substring(1);
        printDecodings(roq1, asf + ch1);

        if(str.length() > 1) {
            int n2 = str.charAt(1) - '0';
            int n = n1 * 10 + n2;
            if(n <= 26) {
                char ch2 = (char)(n + 'a' - 1);
                String roq2 = str.substring(2);
                printDecodings(roq2, asf + ch2);
            }
        }

    }
    //22
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        generateOneByOne("", list, n, n);
        return list;
    }
    public static void generateOneByOne(String sublist, List<String> list, int left, int right){
        if(left > right){
            return;
        }
        if(left > 0){
            generateOneByOne( sublist + "(" , list, left-1, right);
        }
        if(right > 0){
            generateOneByOne( sublist + ")" , list, left, right-1);
        }
        if(left == 0 && right == 0){
            list.add(sublist);
            return;
        }
    }

    public static String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();


        if (m == 0 || n == 0 || "0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        if ("1".equals(num1)) {
            return num2;
        }
        if ("1".equals(num2)) {
            return num1;
        }

        // Result can be maximum of length M + N.

        int[] result = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // Adding previous values in result array into this product.
                product += result[i + j + 1];

                // Adding the new product into the result array
                result[i + j + 1] = product % 10;
                result[i + j] += product / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r : result) {
            if (sb.length() == 0 && r == 0) {
                continue;
            }
            sb.append(r);
        }

        return sb.toString();
    }
    private static void display(int[] n) {
        for(int i = 0; i< n.length; i++) System.out.print(n[i]+" ");
    }

    //49
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<HashMap<Character, Integer>, ArrayList<String>> map = new HashMap<>();

        for(String str : strs) {
            HashMap<Character, Integer> fmap = new HashMap<>();
            for(int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                fmap.put(ch, fmap.getOrDefault(ch, 0) + 1);
            }

            if(map.containsKey(fmap) == true) {
                map.get(fmap).add(str);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(str);
                map.put(fmap, list);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for(List<String> list : map.values()) {
            res.add(list);
        }
        return res;
    }
    static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    static void swap(String[]ar,int i,int j){
        String temp = ar[i]; ar[i] = ar[j]; ar[j] = temp;
    }
    //31
    public static void nextPermutation(int[] A) {
        if(A.length == 0 || A == null) return ;
        int i = A.length-2;
        //step 1
        while(i>=0 && A[i]>=A[i+1]) i--;
        //step 2 & 3
        if(i>=0){
            int j = A.length-1;
            while(A[i]>=A[j]) j--;
            swap(A,i,j);
        }
        //step 4
        reverse(A,i+1,A.length-1);
        display(A);
    }
    public static void reverse(int[] A,int i,int j){

        while(i<j){
            swap(A,i,j);
            i++; j--;
        }
    }
    //47
    public static List<List<Integer>> permuteDuplicate(int[] nums) {
        List<List<Integer>> ans  =  new ArrayList<>();
        boolean [] visited = new boolean[nums.length];
        Arrays.sort(nums); //sorting is necessary to remove the duplicates
        permute2(nums,new ArrayList<>(),ans,visited);
        return ans;
    }

    private static void permute2(int[] nums, ArrayList<Integer> curr, List<List<Integer>> ans, boolean[] visited) {
        if (curr.size() == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if (visited[i] == true ) continue;
            if (i>0 && nums[i] == nums[i-1] && visited[i-1] == true) continue;
            visited[i] = true;
            curr.add(nums[i]);
            permute2(nums, curr, ans, visited);
            curr.remove(curr.size() - 1);
            visited[i] = false;
        }
    }

    //46
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans  =  new ArrayList<>();
        boolean [] visited = new boolean[nums.length];
        permute(nums,new ArrayList<>(),ans,visited);
        return ans;
    }

    private static void permute(int[] nums, ArrayList<Integer> curr, List<List<Integer>> ans, boolean[] visited) {
        if (curr.size() == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if (visited[i] == true) continue;
            visited[i] = true;
            curr.add(nums[i]);
            permute(nums, curr, ans, visited);
            curr.remove(curr.size() - 1);
            visited[i] = false;
        }
    }

    //string permutation
    public static ArrayList<String> getPermutation(String str) {
        if(str.length() == 0){
            ArrayList<String> list = new ArrayList<>(); list.add(""); return list;
        }
        ArrayList<String> mlist = new ArrayList<>();
        for(int i =0;i<str.length();i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1);
            ArrayList<String> list = getPermutation(ros);
            for(String s:list){
                mlist.add(ch+s);
            }
        }
        return mlist;
    }

    public static void getPermutationNum(){
        List<Integer> list = Arrays.asList(1,2,3);
        StringBuilder sb = new StringBuilder();
        list.stream().forEach(i->sb.append(i));
        System.out.println(getPermutation2(sb.toString()));
    }
    public static ArrayList<String> getPermutation2(String str) {
        if(str.length() == 0){
            ArrayList<String> list = new ArrayList<>(); list.add(""); return list;
        }
        ArrayList<String> mlist = new ArrayList<>();
        for(int i =0;i<str.length();i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1);
            ArrayList<String> list = getPermutation2(ros);
            for(String s:list){
                mlist.add(ch+s);
            }
        }
        return mlist;
    }

    //5 DP
    public static String longestPalindrome2(String s) {
        int x =0; int y=0; int n = s.length();
        boolean dp[][] = new boolean[n][n];
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;j++,i++){
                if(gap == 0){
                    dp[i][j] =true;
                }else if(gap == 1){
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }else{
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i+1][j-1];
                }
                if(dp[i][j] == true){
                    x = i; y= j;
                }
            }
        }
        return s.substring(x,y+1);
    }
    //5 //O(n2)
    public static String longestPalindrome(String s) {

        if(s.length() <= 1) return s;
        int max_len = 1;
        int n = s.length();
        int st = 0, end = 0;

        // Odd length
        for(int i = 0; i < n-1; ++i){
            int l = i, r = i;
            while(l >= 0 && r < n){
                if(s.charAt(l) == s.charAt(r)){
                    l--; r++;
                }else
                    break;
            }
            int len = r-l-1;
            if(len > max_len){
                max_len = len;
                st = l+1;
                end = r-1;
            }
        }

        // Even length
        for(int i = 0; i < n-1; ++i){
            int l = i, r = i+1;
            while(l >= 0 && r < n){
                if(s.charAt(l) == s.charAt(r)){
                    l--; r++;
                }else
                    break;
            }
            int len = r-l-1;
            if(len > max_len){
                max_len = len;
                st = l+1;
                end = r-1;
            }
        }

        return s.substring(st,end+1);
    }
    //3
    public int longestSubstringWithNonReaptingCharacter(String str) {
        int ansLen=0; int tempLen=0; int acq=-1; int rel=-1;
        HashMap<Character,Integer> map = new HashMap<>();
        while(true){
            boolean acFlag = false;
            boolean reFlag = false;
            while(acq<str.length()-1){
                acq++;
                acFlag = true;
                char ch = str.charAt(acq);
                map.put(ch,map.getOrDefault(ch,0)+1);
                if(map.get(ch) == 2){
                    break;
                }else{
                    tempLen = acq-rel;
                    ansLen = Math.max(ansLen,tempLen);
                }
            }
            while(rel<acq){
                rel++;
                reFlag = true;
                char ch = str.charAt(rel);
                map.put(ch,map.getOrDefault(ch,0)-1);
                if(map.get(ch) == 1){
                    break;
                }
            }
            if(acFlag == false && reFlag == false)
                break;
        }
        return ansLen;
    }
    //3
    public static int lengthOfLongestSubstring(String s) {
        int count = 0; int max = 0; int left = 0,right = 0;
        Set<Character> set = new HashSet<>();
        while(right < s.length()){
            if(!set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                count++; right++;
            }else{
                set.remove(s.charAt(left));
                count--; left++;
            }
            max = Math.max(max,count);
        }

        return max;
    }
    //17
    static String[] codeArr = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public static void printKPC(String str, String asf,List<String> ans) {
        if(str.length() == 0) {
            // System.out.println(asf);
            ans.add(asf);
            return;
        }
        int indx = str.charAt(0) - '0';
        String code = codeArr[indx];
        String roq = str.substring(1);

        for(int i =0; i < code.length(); i++) {
            char option = code.charAt(i);
            printKPC(roq, asf + option,ans);
        }
    }

    public static List<String> letterCombinations(String str) {
        List<String> ans = new ArrayList<>();
        printKPC(str,"",ans);
        return ans;
    }
    //12 Int to roman
    public String intToRoman(int num) {
        HashMap<Integer, String> map = new HashMap<>();
        String ans = "";
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(400, "CD");
        map.put(4, "IV");
        map.put(9, "IX");
        map.put(40, "XL");
        map.put(90, "XC");
        int arr[] = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        for(int i = arr.length-1;i>=0;i--){

            while(num>=arr[i]){
                ans = ans + map.get(arr[i]);
                num = num - arr[i];
            }
            if(num == 0)
                break;
        }
        return ans;
    }
    //8
    public static int myAtoi(String s) {

        if(s.length() == 0 || s == null)
            return 0;
        int flag = 1;
        int i = 0; //removing the leading zeros 00032->32
        while(i < s.length() && s.charAt(i) <= 32)
            i++;

        if(i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')){
            if(s.charAt(i) == '-')
                flag *= -1;
            i++;
        }
        long num = 0;
        for(;i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9';i++){
            num = num * 10 + s.charAt(i)-'0';
            if( num * flag >Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            else if(num * flag < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
        }
        return (int)(flag == 1?num:num * (-1));
    }
}
