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
