package dsa;

import java.util.Arrays;

public class DP1 {
    public static void main(String[] args) {
       // coinChange();
        houseRobber();
    }
    private static void houseRobber() {
        int [] house = {1,2,3,1};
        int n = house.length;
       // int res = recursion(house,house.length-1);
        int [] dp = new int[n+1];
        Arrays.fill(dp,-1);
       // int res = memorization(house,n-1,dp);
        int res = tabulation(house,n-1,dp);
        System.out.println(res);
    }
    private static int tabulation(int[] house, int n, int[] dp) {
       dp[0]  = house[0];
       for (int i=1;i<=n;i++){
           int temp = 0;
           if (i-2 >= 0){
               temp = dp[i-2];
           }
           int include = temp + house[i];
           int exclude = dp[i-1];
           dp[i] = Math.max(include,exclude);
       }
       return dp[n];
    }
    private static int memorization(int[] house, int n,int []dp) {
        if(n<0) {
            return 0;
        }
        if(dp[n]!=-1) return dp[n];
        int include = memorization(house,n-2,dp) + house[n];
        int exclude = memorization(house,n-1,dp);
        return dp[n] = Math.max(include,exclude);
    }
    private static int recursion(int[] house,int n) {
        if(n<0) {
            return 0;
        }
        int include = recursion(house,n-2) + house[n];
        int exclude = recursion(house,n-1);
        return Math.max(include,exclude);
    }
    public static void coinChange(){
        int [] coin = {1,2,5}; int target = 11;
        int [] coin2 = {2}; int target2 = 3;
       int res = coinChangeBottomUpDp(coin,target);
       System.out.println(res);
       
    }
    public static int memoization(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, -1);
        int res = recursion(coins, amount, 0, dp);
        return res == Integer.MAX_VALUE  ? -1 : res;
    }
    public static int recursion(int[] coins, int amount, int number, int[] dp){
        if(amount == 0)
            return 0;
        int res = Integer.MAX_VALUE;
        for(int i=0; i<coins.length; i++){
            int temp = 0;
            if(amount-coins[i]>=0){
                if(dp[amount-coins[i]] != -1)
                    temp = dp[amount-coins[i]];
                else
                    temp = recursion(coins, amount-coins[i], number+1, dp);
                if(temp != Integer.MAX_VALUE)
                    res = Math.min(res, temp+1);
            }
        }
        return dp[amount] = res;
    }
    private static int recCoinChange(int []coins, int amount,int number){
        if(amount == 0)
            return number;
        if(amount<0)
            return Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        for(int i=0; i<coins.length; i++)
            res = Math.min(res, recCoinChange(coins, amount-coins[i], number+1));
        return res;
    }
    private static int coinChangeBottomUpDp(int []coins, int amount){
        /*
           0 1 1 2 2 1 2 2 3 3 2 3 3   (minimum coin required tp achieve target)
         */
        int dp[] = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        if(dp[amount] != Integer.MAX_VALUE){
            return dp[amount];
        }
        for(int i=1; i<=amount; i++){
            int min = Integer.MAX_VALUE;
            for(int j=0; j<coins.length; j++){
                int res = 0;
                if(i-coins[j]>=0){
                    res = dp[i-coins[j]];
                    if(res != Integer.MAX_VALUE){
                        min = Math.min(min, res+1);
                    }
                }
            }
            dp[i] = min;
        }
        System.out.println("****DP*****");
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i]+" ");
        }
        return dp[amount]!=Integer.MAX_VALUE?dp[amount]:-1;
    }

}
