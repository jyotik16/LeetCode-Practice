package dsa;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.IntStream;

class EasyStringsQ{
    public static void main(String[] args) {
       // System.out.println(romanToInt("LVIII"));
       // System.out.println(addBinary("11","1"));
        char [] ch = {'h','e','l','l','o'};
      //  reverseString(ch);
      //  System.out.println(canConstruct("aa","aabcc"));
      //  System.out.println(reverseStr("abcdefgi",3));
      //  System.out.println(addStrings("456","77"));
      //  System.out.println(wordPattern2("abab","dog cat cat dog"));
      //  System.out.println(isSubsequence2("abc","asdbdfc"));
      //  System.out.println(fizzBuzz(15));
      //  System.out.println(countSegments(", , , ,        a, eaefa"));
      //  System.out.println(repeatedSubstringPattern("abcdabcdabcd"));
      //  System.out.println(convertToTitle(701));
        // System.out.println(letterCombinations("23"));
      //  System.out.println(licenseKeyFormatting("--a-a-a-a--",2));
      //  System.out.println(buddyStrings("aaaaaaabc","aaaaaaacb"));
        String []in = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
      //  System.out.println(numUniqueEmails(in));
       // System.out.println(myAtoi("  +  42"));
        int [] n = {1,2,1};
      //  sortColors(n);
      //  display(n);
     //   System.out.println(getPermutation("abc"));
      //  getPermutationNum();
     //   System.out.println(permuteDuplicate(n));

       // nextPermutation(a);
     //  System.out.println(reverseWords("  sky is    blue  "));
        String id = "P_sdf-3453";
        int x = id.indexOf("P");
        System.out.println(x);
        System.out.println(id.substring(x+2));

     //   StringReverse("blue is sky the");
       Solve();
    }

    private static void Solve() {
        //A = "abcabbccd"
        //B = 3
        //We can change both 'a' and one 'd' into 'b'.So the new string becomes "bbcbbbccb".
        //So the minimum number of distinct character will be 2.
       // MinimizedDistinctCharacters("abcabbccd",3);
        String [] st = {"abcdefgh", "abefghijk", "abcefgh"};
       String ans =  LongestCommonPrefix(st);
        System.out.println("Longest Common Prefix:"+ans);
    }

    private static String LongestCommonPrefix(String [] A) {
        String shortest = A[0];
        for (String str : A) {
            if (str.length() < shortest.length()) {
                shortest = str;
            }
        }
        int length = shortest.length();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char currentChar = shortest.charAt(i);
            for (String str : A) {
                if (str.charAt(i) != currentChar) {
                    return result.toString();
                }
            }
            result.append(currentChar);
        }
        System.out.println(result.toString());
        return result.toString();
    }


    public static int MinimizedDistinctCharacters(String A, int B) {
        int[] freq = new int[26];
        char ar[] = A.toCharArray();
        int n = ar.length;
        for(int i=0;i<n;i++){
            int index = ar[i]-'a';
            freq[index]++;
        }
        int total=0;
        for(int i=0;i<26;i++){
            if(freq[i]>0){
                total++;
            }
        }
        Arrays.sort(freq);
        int change=0;
        for(int i=0;i<26;i++){
            if(B-freq[i]>=0 && freq[i]!=0){
                B=B-freq[i];
                change++;
            }
        }
        return Math.max(total-change,1);
    }

    public static String StringReverse(String A) {
        StringBuilder ans = new StringBuilder(); //blue is sky the
        int lastInd = A.length();
        for (int i = A.length() - 1; i >= 0; i--) {
            if (A.charAt(i) == ' ') {
                String subStr = A.substring(i + 1, lastInd);
                ans.append(subStr);
                if (A.charAt(lastInd - 1) != ' ') {
                    ans.append(' ');
                }
                lastInd = i;
            }
        }
        if (lastInd > 0) {
            ans.append(A.substring(0, lastInd));
        }
        return ans.toString(); //the sky is blue
    }
        //151
    public static String reverseWords(String s) {
        s = s.strip();
        String [] ar = s.split(" ");
        //reverse
        reverse(ar,0,ar.length-1);
        // remove multiple space b/w the strings
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < ar.length; i++){
           // System.out.println(ar[i]);
            String ss  = ar[i];
            if(ss.equalsIgnoreCase(" ")) continue;
            if(ss.equalsIgnoreCase("")) continue;
            sb.append(ar[i]+" ");
        }
       return sb.toString().strip();
    }
    public static void reverse(String[] A,int i,int j){
        while(i<j){
            swap(A,i,j);
            i++; j--;
        }
    }
    static void swap(String[]ar,int i,int j){
        String temp = ar[i]; ar[i] = ar[j]; ar[j] = temp;
    }

    private static void display(int[] n) {
        for(int i = 0; i< n.length; i++) System.out.print(n[i]+" ");
    }

    static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //929
    public static int numUniqueEmails(String[] emails) {
        int count =0;
        Set<String> set = new HashSet<>();
    for(String email:emails){
        if(email.charAt(0) == '+' || email.charAt(0) == '.')
           continue;
        int atIndex = email.indexOf("@");
        int plusIndex= email.indexOf("+");
        String str1 = "";
        if(plusIndex>=0)
            str1 = email.substring(0,plusIndex);
        else
            str1 = email.substring(0,atIndex);

        str1 = str1.replace(".","");
        str1 = str1 + email.substring(atIndex);
        System.out.println(str1);
        if(set.add(str1)) count++;
    }
    return count;
    }
    //859
    public static boolean buddyStrings(String s, String g) {
        if(s.length() != g.length()) return false;
        if(s.equals(g)){
            Set<Character> set = new HashSet<>();
            for(int i=0;i<s.length();i++){
                if(set.contains(s.charAt(i)))
                    return true;
                set.add(s.charAt(i));
            }
             return false;
        }else{
            List<Integer> indx = new ArrayList<>();
            for(int i =0;i<s.length();i++){
                if(s.charAt(i) != g.charAt(i))
                    indx.add(i);
            }

            return indx.size() == 2 && s.charAt(indx.get(0)) == g.charAt(indx.get(1)) && s.charAt(indx.get(1)) == g.charAt(indx.get(0));
        }

    }
    //482
    public static String licenseKeyFormatting(String s, int k) {
        StringBuilder ans = new StringBuilder();
        int count = 0;
        for(int i=s.length()-1; i>=0; i--){
            char ch = s.charAt(i);

            if(Character.isLetter(ch)){
                ans.append((""+ch).toUpperCase()); count++;
            } else if (Character.isDigit(ch)) {
                ans.append(ch+""); count++;
            }
            if( (i!=0 || i!=s.length()-1) && count == k){
                ans.append("-"); count=0;
            }
        }
        ans = ans.reverse();
        if(ans.length()>1 && ans.charAt(0) == '-'){
            ans.deleteCharAt(0);
        }
        return ans.toString();
    }
    //168
    public static String convertToTitle(int num) {
        StringBuilder sb = new StringBuilder();
        while(num != 0){
            int m = (num-1)%26;
            int v = (num-1)/26;
            sb.append((char)('A'+m));
            num = v;
        }
        return sb.reverse().toString();
      //  return num ==0 ?" ":convertToTitle(--num/26)+ (char)('A'+num%26);
    }

    public static boolean repeatedSubstringPattern2(String s) {
        ArrayList<Integer> divisors = new ArrayList<>();
        for(int i=1; i<s.length()/2+1; ++i){
            if(s.length() % i == 0){
                divisors.add(i);
            }
        }
        for(int i=0; i<divisors.size(); ++i){
            String temp = s.substring(0, divisors.get(i));
            boolean flag = true;
            for(int j=0; j<s.length(); ++j){
                if(s.charAt(j) != temp.charAt((int)(j%temp.length()))){
                    flag = false;
                    break;
                }
            }
            if(flag == true)
                return true;
        }
        return false;
    }
    //459
    public static boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for(int i=1; i<=n/2; i++){
            String res = s.substring(0,i);
            if(n % i == 0){                                           //Line 6
                int a = n / i;
                if((res.repeat(a)).equals(s)) return true;
            }
        }
        return false;
    }
    //434
    public static int countSegments(String s) {
        s = s.strip();
        if(s.length() == 0 || s.charAt(0) ==' ')
            return 0;
        int count = 1;
        char [] ar = s.toCharArray();
        for(int i=0;i<ar.length;i++){
            if(ar[i]==' ' && ar[i+1]!=' '){
                count++;
            }
        }
        return count;
    }
    //412
    public static List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
    for(int i=1;i<=n;i++){
        if (i%15==0){
            ans.add("FizzBuzz");
        }else if (i%3==0){
            ans.add("Fizz");
        }
        else if (i%5==0){
            ans.add("Buzz");
        }else{
            ans.add(String.valueOf(i));
        }
    }
    return ans;
    }
    //329
    public static boolean isSubsequence2(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }

        for (char c: s.toCharArray()) {
            if (t.contains(String.valueOf(c)) == false){
                return false;
            }

            t = t.substring(t.indexOf(c) + 1);
        }

        return true;
    }
    //329
    public static boolean isSubsequence(String s, String t) {
        if(s.length()==0){
            return true;
        }
        if(s.length()>t.length()){
            return false;
        }
        int k=0;
        for(int i=0;i<t.length()&&k<s.length();i++)
            if(t.charAt(i)==s.charAt(k)){
                k++;
            }

        return k==s.length();

    }
    //290
    public static boolean wordPattern(String pattern, String s) {
        Map<Character, String> cMap = new HashMap<>();
        Map<String, Character> wMap = new HashMap<>();
        String[] words = s.split(" ");
        int n = pattern.length();
        if (n != words.length) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            if (cMap.containsKey(c)) {
                if (!cMap.get(c).equals(word)) {
                    return false;
                }
            }
            else {
                cMap.put(c, word);
            }
            if (wMap.containsKey(word)) {
                if (!wMap.get(word).equals(c)) {
                    return false;
                }
            } else {
                wMap.put(word, c);
            }
        }
        return true;
    }
    //290
    public static boolean wordPattern2(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (Integer i=0; i<words.length; ++i)
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }

    public static String addStrings(String num1, String num2) {
        int i = num1.length()-1;
        int j = num2.length()-1;
        int c = 0;
        int sum = 0;
        StringBuilder ans = new StringBuilder();
        while (i >=0 || j >=0) {
            sum = 0;
            int d1 = i<num1.length() && i>=0?num1.charAt(i)-'0':0;;
            int d2 = j<num2.length() && j>=0?num2.charAt(j)-'0':0;
            sum = (d1 + d2 + c);
            c = sum/10;
            ans.append(sum%10);
            i--;
            j--;
        }
        if (c != 0) {
            ans.append(c);
        }
        return ans.reverse().toString();
    }
    //541 O(n) O(n)
    public static String reverseStr(String s, int k) {
        var res = new StringBuilder();
        boolean reverse = true;
        for (int i=0;i<s.length();i+=k){
            var str = i+k+1>s.length() ? s.substring(i) : s.substring(i,i+k);
            res.append(reverse ? new StringBuilder(str).reverse() : str);
            reverse = !reverse;
        }
        return res.toString();
    }
    //541 O(n) O(1)
    public String reverseStr2(String s, int k) {
        if(k==1) return s;
        char[] arr = s.toCharArray();

        for(int i = 0; i<s.length(); i+=2*k)
            reverse(arr, i, Math.min(i+k-1, s.length()-1));

        return new String(arr);
    }
    public void reverse(char[] arr, int i , int j){
        while(i<=j){
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;j--;
        }
    }

    public static int romanToInt(String s) {
        if(s==null) return 0;
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('I',1); map.put('V',5); map.put('X',10);    map.put('L',50);
        map.put('C',100);   map.put('D',500);   map.put('M',1000);

        int n = s.length();
        int sum = map.get(s.charAt(s.length()-1));

        for(int i=0; i< s.length()-1; i++){
            int cur = map.get(s.charAt(i));
            int pre = map.get(s.charAt(i+1));
            if(pre > cur)
                sum -= cur;
            else
                sum += cur;
        }
        return sum;
    }

    public static String longestCommonPrefix(String [] strs){
        int mini = Integer.MAX_VALUE;
        int minIndex = 0;
        for(int i=0 ; i<strs.length ; i++){
            if(strs[i].length()<mini){
                mini = strs[i].length();
                minIndex = i;
            }
        }

        // Then I have checked for every character of the test string in the given string array...
        String test = strs[minIndex];
        String ans = "";
        for(int i=0 ; i<test.length() ; i++){
            for(int j=0 ; j<strs.length ; j++){
                if(test.charAt(i)!=strs[j].charAt(i)){
                    return ans;
                }
            }
            ans += test.charAt(i);
        }
        return ans;
    }

    public static boolean validParentheses(String s){
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            Character ch = s.charAt(i);
            if(ch=='(' || ch=='[' || ch=='{'){
                st.push(ch);
            }else if(ch==')'){
                if(st.size()==0 || st.peek() != '(')
                    return false;
                st.pop(); //remove the (
            }else if(ch==']'){
                if(st.size()==0 || st.peek() != '[')
                    return false;
                st.pop(); //remove the (
            }else if(ch=='}'){
                if(st.size()==0 || st.peek() != '{')
                    return false;
                st.pop(); //remove the (
            }
        }
        return st.size() == 0;
    }

    public static int lengthOfLastWord(String s) {
        s = s.strip();
        String [] ar = s.split(" ");
        String str = ar[ar.length-1];
        System.out.println(str.length());
        return str.length();
    }

    public boolean isPalindrome(String s) {
        List<Character> chars = new ArrayList<>();
        boolean flag = true;
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i))) {
                chars.add(s.charAt(i));
            }
        }
        for (int j = 0; j < chars.size(); j++) {
            if (chars.get(j) != chars.get(chars.size() - 1 - j)) {
                flag = false;
            }
        }
        return flag;
    }
    //O(m+n)
    public static String addBinary(String a, String b) {
        int i = a.length()-1;
        int j = b.length()-1;
        int c = 0;
        int sum = 0;
        StringBuilder ans = new StringBuilder();
        while (i >=0 || j >=0) {
            sum = c;
            int d1 = 0;
            if (i < a.length() && i>=0 && a.charAt(i) == '1') {
                d1 = 1;
            }
            int d2 = 0;
            if (j < b.length() && j>=0 && b.charAt(j) == '1') {
                d2 = 1;
            }
            sum += (d1 + d2);
            c = sum > 1 ? 1 : 0;
            ans.append(sum % 2);
            i--;
            j--;
        }
        if (c == 1) {
            ans.append(c);
        }
        return ans.reverse().toString();
    }

    public static void reverseString(char[] str) {
        int n = str.length;
        for(int i=0;i<n/2;i++){
            char ch = str[i];
            str[i] = str[n-i-1];
            str[n-i-1] = ch;
        }
        for(Character c:str){
            System.out.print(c+" ");
        }
    }
    //383
    public static  boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> fmap = new HashMap<>();
        for(int i = 0; i < ransomNote.length(); i++) {
            char ch = ransomNote.charAt(i);
            fmap.put(ch, fmap.getOrDefault(ch, 0) + 1);
        }
        for(int i = 0; i < magazine.length(); i++) {
            char ch = magazine.charAt(i);
            if(fmap.containsKey(ch) == true) {
                fmap.put(ch, fmap.get(ch) - 1);
                if (fmap.get(ch) == 0) fmap.remove(ch);
            }
        }
        return fmap.size() == 0;

    }
}