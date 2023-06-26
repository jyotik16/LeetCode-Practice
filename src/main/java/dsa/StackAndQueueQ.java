package dsa;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Stack;

public class StackAndQueueQ {
    public static void main(String[] args) {
        int [] in = {5,3,8,-2,7};
        String s = "((a+b)+(c+d))";
        int [] num ={1,2,3,4,3};
        int [] res = nextGreaterElements(num);
        display(res);
    }


    static void display(int[] res){
        for (int i=0;i<res.length;i++){
            System.out.print(res[i]+" ");
        }
    }
    //503
    public static int[] nextGreaterElements(int[] nums) {
        // corner case
        if(nums == null) return null;
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack  = new Stack<>(); // to store idx
        for(int i = 0; i < 2 * n; i++){
            int num = nums[i % n];
            while(!stack.isEmpty() && nums[stack.peek()] < num){
                int idx = stack.pop();
                res[idx] = num;
            }
            if(i < n) stack.push(i);
        }
        // set the max's greater element to be -1
        while(!stack.isEmpty()){
            int idx = stack.pop();
            res[idx] = -1;
        }
        return res;

    }
    //Leetcode Dupliacte Brackets
    public static boolean isDuplicateBrackets(String s) {
        //String s = "((a+b)+(c+d))"; true
        Stack<Character> st = new Stack<>();

        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == ')'){
                while(st.size()>0 && st.peek()!='('){
                    st.pop();
                }
                st.pop(); // remove ->(
            }else if(ch == '}'){
                while(st.size()>0 && st.peek()!='{'){
                    st.pop();
                }
                st.pop(); // remove ->{
            }else if(ch == ']'){
                while(st.size()>0 && st.peek()!='['){
                    st.pop();
                }
                st.pop(); // remove ->[
            }else{
                st.push(ch);
            }
        }
        return st.size()==0;
    }
    // Leetcode 20
    public boolean isValid(String s) {
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
                st.pop(); //remove the [
            }else if(ch=='}'){
                if(st.size()==0 || st.peek() != '{')
                    return false;
                st.pop(); //remove the {
            }
        }
        return st.size() == 0;
    }
    private int[] nsrIndex(int[] arr) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        int[] res = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = arr.length;
        }
        return res;
    }
    private int[] nslIndex(int[] arr) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        int[] res = new int[arr.length];
        for(int i = arr.length - 1; i >= 0; i--) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }
    // largest area histogram, leetcode 84
    public int largestRectangleArea(int[] heights) {
        int[] nsr = nsrIndex(heights);
        int[] nsl = nslIndex(heights);
        int ans = 0;
        for(int i = 0; i < heights.length; i++) {
            int area = (nsr[i] - nsl[i] - 1) * heights[i];
            ans = Math.max(ans, area);
        }
        return ans;
    }
    // maximal rectangle, leetcode 85
    public int maximalRectangle(char[][] matrix) {
        int[] ht = new int[matrix[0].length];
        int res = 0;
        for(int i = 0; i < ht.length; i++) {

            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '0') {
                    ht[j] = 0;
                } else {
                    ht[j] += 1;
                }
            }
            res = Math.max(res, largestRectangleArea(ht));
        }
        return res;
    }
    //503
    public static int[] nextGreaterElement_503(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        for (int i=n-2;i>=0;i--){
            while(st.size() >0 && st.peek() <= nums[i]){
                st.pop();
            }
            st.push(i);
        }
        //above loop ka purpose yehi hai ki issme sabse chota element index save hogya hai
        int [] res = new int[n];
        for (int i=n-1;i>=0;i--){
            while(st.size() >0 && st.peek() <= nums[i]){
                st.pop();
            }
            res[i] = st.size()==0?-1:st.peek();
            st.push(nums[i]);
        }
        return res;
    }
    //496
    public static int[] nextGreaterElement_496(int[] query, int[] arr) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                map.put(arr[st.pop()], arr[i]);
            }
            st.push(i);
        }

        int[] res = new int[query.length];
        for(int i = 0; i < res.length; i++) {
            res[i] = map.getOrDefault(query[i], -1);
        }
        return res;
    }
    //print the array element Index in output
    public static int[] nextSmallerElementsToLeft(int[] arr) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        int[] res = new int[arr.length];
        for(int i = arr.length-1; i >=0 ;i--) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    public static int[] nextGreaterElementsToLeft(int[] arr) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        int[] res = new int[arr.length];
        for(int i = arr.length-1; i >=0 ;i--) {
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    public static int[] nextSmallerElementsToRight(int[] arr) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        int[] res = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    public static int[] nextGreaterElementsToRight(int[] arr) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        int[] res = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }
    //print the array element in output
    public static int[] nextSmallerElementLeft(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        int [] res = new int[n];
        for (int i=0;i<n;i++){
            while(st.size() >0 && st.peek() >= nums[i]){
                st.pop();
            }
            res[i] = st.size()==0?-1:st.peek();
            st.push(nums[i]);
        }
        return res;
    }

    public static int[] nextGreaterElementLeft(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        int [] res = new int[n];
        for (int i=0;i<n;i++){
            while(st.size() >0 && st.peek() <= nums[i]){
                st.pop();
            }
            res[i] = st.size()==0?-1:st.peek();
            st.push(nums[i]);
        }
        return res;
    }

    public static int[] nextSmallerElementRight(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        int [] res = new int[n];
        for (int i=n-1;i>=0;i--){
            while(st.size() >0 && st.peek() >= nums[i]){
                st.pop();
            }
            res[i] = st.size()==0?-1:st.peek();
            st.push(nums[i]);
        }
        return res;
    }

    public static int[] nextGreaterElementRight(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        int [] res = new int[n];
        for (int i=n-1;i>=0;i--){
            while(st.size() >0 && st.peek() <= nums[i]){
                st.pop();
            }
            res[i] = st.size()==0?-1:st.peek();
            st.push(nums[i]);
        }
        return res;
    }
    //994
    static class Orange{
        int r; int c; int t;
        Orange(int r,int c,int t){
            this.r = r; this.c = c; this.t = t;
        }
    }
    static int[] xdir = {-1, 0, 1, 0};
    static int[] ydir = {0, -1, 0, 1};
    public static int orangesRotting(int[][] grid) {
        int totalOranges = 0;
        Stack<Orange> st = new Stack<>();
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                if (grid[i][j] == 2){
                    st.push(new Orange(i,j,0));
                }
                if (grid[i][j] == 1 || grid[i][j] == 2)
                    totalOranges++;
            }
        }
        int time = 0;
        while(st.size()>0){
            Orange rem = st.pop();
            if (grid[rem.r][rem.c] == -2) continue;
            grid[rem.r][rem.c] = -2;
            totalOranges--;
            time = rem.t;
            for (int d=0;d<4;d++){
                int x = rem.r + xdir[d];
                int y = rem.c + ydir[d];

                if(x>=0 && x<grid.length && y>=0 && y<grid[0].length && grid[x][y] == 1){
                    st.push(new Orange(x,y,time+1));
                }
            }
        }
        return totalOranges == 0?time:-1;
    }

}
