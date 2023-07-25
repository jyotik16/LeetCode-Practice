package dsa;

import java.util.ArrayList;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {
        int n = 2;
       // toh(n);
       int [] nums = {1,2,3};
     //   generateCombinations(nums,new ArrayList<>(),0);
      //  combine(4,2);
        printPermutation("abc","");
    }
    private static ArrayList < ArrayList < Integer >> ans;
    public static ArrayList<ArrayList < Integer >> combine(int A, int B) {
        ans = new ArrayList < > ();
        ArrayList < Integer > cur = new ArrayList < > ();
        solve(1, cur, A, B);
        System.out.println(ans);
        return ans;
    }
     static void solve(int idx, ArrayList < Integer > cur, int A, int B) {
        if (cur.size() == B) {
            ans.add(new ArrayList < > (cur));
            return;
        }
        if (idx == A + 1)
            return;
        // Include current element
        cur.add(idx);
        solve(idx + 1, cur, A, B);
        cur.remove(cur.size() - 1);
        // Don't include current element
        solve(idx + 1, cur, A, B);
    }

    static void toh(int N){
        int src = 1;
        int help = 2;
        int dest = 3;
        int idx=0;
        int m = (1<<N)-1;
        int [][]res = new int [m][3];
        tower(N, src, help, dest, res, idx);

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res.length; j++) {
                System.out.print(res[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static int[][] solve(int n,int A,int B,int C) {
        if(n ==0) return new int[1][0];
        solve(n-1,A,C,B);
        System.out.println(n+" "+A+" "+B);
        solve(n-1,C,B,A);
        return new int[0][];
    }

    public static int tower(int n, int src, int help, int dest, int [][]res, int idx){
        if(n == 0){
            return idx;
        }
        idx = tower(n-1, src, dest, help, res, idx);
        res[idx][0] = n;
        res[idx][1] = src;
        res[idx][2] = dest;
        idx++;
        idx = tower(n-1, help, src, dest, res, idx);
        return idx;
    }

    public static void generateCombinations(int[] nums, List<List<Integer>> combination , int index) {
        if (index == nums.length) {
            System.out.println(combination);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            List<Integer> subans = new ArrayList<>();
            subans.add(nums[1]);
            combination.add(subans);
            generateCombinations(nums, combination, index + 1);
        }
    }

    public static void printPermutation(String str, String asf) {
        if(str.length() == 0) {
            System.out.println(asf);
            return;
        }

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String roq = str.substring(0, i) + str.substring(i + 1);
            printPermutation(roq, asf + ch);
        }
    }
    public void grayCodeRecurs(ArrayList<Integer> ans,int A){
        if(A==1){
            ans.add(0);
            ans.add(1);
            return ;
        }
        grayCodeRecurs(ans,A-1);
        for(int i=ans.size()-1;i>=0;i--){
            ans.add(ans.get(i)+(int)Math.pow(2,A-1));
        }
        return;
    }
    public ArrayList<Integer> grayCode(int A) {
        ArrayList<Integer> ans=new ArrayList<>();
        grayCodeRecurs(ans,A);
        return ans;
    }
}
