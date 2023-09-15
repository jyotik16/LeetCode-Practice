package dsa;

import java.util.*;

public class SlidingWindow {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
       // String res = minWindow(s,t);
        //System.out.println(res);
       // System.out.println(s.substring(9,13));
        int [] ar = {1,1,3};
       // sumoflength(ar,ar.length);
       // search();
    }
    //https://leetcode.com/problems/count-subarrays-with-fixed-bounds/submissions/
    public long countSubarrays(int[] A, int minK, int maxK) {
        long res = 0, badIndx = -1, jmin = -1, jmax = -1, n = A.length;
        for (int i = 0; i < n; ++i) {
            if (A[i] < minK || A[i] > maxK)
                badIndx = i;
            if (A[i] == minK)
                jmin = i;
            if (A[i] == maxK)
                jmax = i;
            long smaller = Math.min(jmin, jmax);
            res += Math.max(0L, smaller - badIndx);
        }
        return res;
    }
    //https://leetcode.com/problems/contains-duplicate-ii/
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int i=0,j=0; int n = nums.length;
        HashSet<Integer> set = new HashSet<>();
        while(j<n){
            if(Math.abs(j-i)>k){
                set.remove(nums[i]);
                i++;
            }
            if (set.contains(nums[j])){
                return true;
            }else{
                set.add(nums[j]);
            }
            j++;
        }
        return false;
    }
    //O(n) O(n)
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (k == 0) return false;

        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int integer = nums[i];
            if (hashMap.containsKey(integer) && i - hashMap.get(integer) <= k)
                return true;
            hashMap.put(integer, i);
        }
        return false;
    }
    //https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
    public long[] printFirstNegativeInteger(long A[], int N, int K) {
        ArrayList<Long> list = new ArrayList<>();
        LinkedList<Long> pq = new LinkedList(); //why LL becoz order need to maintain
        int i = 0, j = 0;
        while(j<N){
            if(A[j]<0){
                pq.add(A[j]);
            }
            if(j-i+1 == K){
                long val = pq.isEmpty()==true?0:pq.peek();
                list.add(val);
                if(A[i]<0 && !pq.isEmpty()){ //that mean first element is neg
                    pq.remove();
                }
                i++;
            }
            j++;
        }
        long[] ans = new long[list.size()];
        j=0;
        for (long ele:list){
            ans[j] = ele; j++;
        }
        return ans;
    }

    private static void search() {
        String txt = "forxxorfxdofr";
        String pat = "for";
        countAnagrams(txt, pat);
    }
    //https://practice.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1
    //Not working
    static int countAnagrams(String pat, String txt) {
        int n = txt.length();
        int m = pat.length();
        int i=0,j=0;
        if(n<m) return 0;
        HashMap<Character,Integer> map = new HashMap<>();
        for (int k = 0; k < m; k++) {
            char ch = pat.charAt(k);
            if(!map.containsKey(ch)){
                map.put(ch,1);
            }else{
                map.put(ch, map.getOrDefault(ch,0)+1);
            }
        }
        int windowSize = m;
        int countOccurrence = 0;
        while(j<n){
            char ch = txt.charAt(j);
            if(map.containsKey(ch) && map.get(ch)>0){
                int c = map.get(ch);
                map.put(ch,c-1);
            }
            if(windowSize == (j-i+1)){
                if(allZeros(map))
                    countOccurrence++;
                char chi = txt.charAt(i);
                if(map.containsKey(chi)){
                    int c = map.get(chi);
                    map.put(chi,c+1);
                }
                i++;
            }
            j++;
        }
        return countOccurrence;
    }
    private static boolean allZeros(HashMap<Character, Integer> map) {
        for (char key:map.keySet()) {
            if (map.get(key) != 0)
                return false;
        }
        return true;
    }

    public static int sumoflength(int[] arr, int n) {

        // For maintaining distinct elements.
        Set<Integer> s = new HashSet<>();

        // Initialize ending point and result
        int j = 0, ans = 0;

        // Fix starting point
        for (int i = 0; i < n; i++)
        {
            while (j < n && !s.contains(arr[j]))
            {
                s.add(arr[j]);
                j++;
            }

            // Calculating and adding all possible length
            // subarrays in arr[i..j]
            ans += ((j - i) * (j - i + 1)) / 2;

            // Remove arr[i] as we pick new starting point
            // from next
            s.remove(arr[i]);
        }

        return ans;
    }
    public static String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        HashMap<Character,Integer> map = new HashMap<>();
        //add into the hashMap
        for(int i=0;i<m;i++){
            char ch = t.charAt(i);
            if(map.containsKey(ch) == false){
                map.put(ch,1);
            }else{
                int c = map.get(ch);
                map.put(ch,c+1);
            }
        }

        int requiredCount = t.length();
        int i = 0, j  = 0;
        int minStart  = 0;
        int minWindow = Integer.MAX_VALUE;
        //window mai iterate kro
        while(j<n){
            char ch1 = s.charAt(j);
            if(map.containsKey(ch1) == true){
                if(map.get(ch1)>0)
                    requiredCount--;
                int c = map.get(ch1);
                map.put(ch1,c-1);
            }else{
                map.put(ch1,-1);
            }
            //window ko shrink kro
            while(requiredCount == 0 && i<n){
                if(minWindow > j-i+1) {
                    minWindow = j-i+1;
                    minStart  = i;
                }
                char ch2 = s.charAt(i);
                if(map.containsKey(ch2) == true ){
                    int c1 = map.get(ch2);
                    map.put(ch2,c1+1);
                    if (map.get(ch2) > 0)
                        requiredCount++;
                }
                i++;
            }
            j++;
        }
        String sub = "";
        if(minWindow == Integer.MAX_VALUE){
            return sub;
        }else {
            sub = s.substring(minStart,minStart+minWindow);
            return sub;
        }
    }
}
